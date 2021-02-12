package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MovieListService;
import vo.ActionForward;
import vo.MovieList;
import vo.PageInfo;

public class MovieListAction implements Action {

	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<MovieList> movieList=new ArrayList<MovieList>();
	      int page=1;
	      int limit=8;
		
	      if(request.getParameter("page")!=null){
	          page=Integer.parseInt(request.getParameter("page"));
	       }
	      
		MovieListService MoiveListService = new MovieListService();
		
		
		  int listCount = MoiveListService.getListCount(); 	//영화 게시글 개수
	      
	      movieList = MoiveListService.getMovieList(page,limit);
	      
	      int maxPage=(int)((double)listCount/limit+0.95); 	//마지막 페이지
	      
	      int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1; //첫 페이지
	      
	      int endPage = startPage+10-1; //끝 페이지
	      
	      if (endPage > maxPage) endPage = maxPage;
	      
	      PageInfo pageInfo = new PageInfo();
	      pageInfo.setEndPage(endPage);
	      pageInfo.setListCount(listCount);
	      pageInfo.setMaxPage(maxPage);
	      pageInfo.setPage(page);
	      pageInfo.setStartPage(startPage);   
		
	    request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("movieList", movieList);
		
		ActionForward forward = new ActionForward("movieList.jsp",false);
		
		return forward;
	}

}
