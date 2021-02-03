package action;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.NewsBean;
import vo.PageInfo;
import svc.NewsListService;
import vo.ActionForward;


public class NewsListAction implements Action {

	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<NewsBean> newsBean = new ArrayList<NewsBean>();
	    int page=1;
	    int limit=6;
		
	    if(request.getParameter("page")!=null){
	        page=Integer.parseInt(request.getParameter("page"));
	     }
	    
		NewsListService newsListService = new NewsListService();
		
		
		int listCount = newsListService.getListCount(); 	//�� ���ڵ� ��
	    
		newsBean = newsListService.getNewsList(page,limit);
	    
	    int maxPage=(int)((double)listCount/limit+0.95); 	//������ ��
	    
	    int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
	    
	    int endPage = startPage+10-1;
	    
	    
	    
	    if (endPage > maxPage) endPage = maxPage;
	    
	    PageInfo pageInfo = new PageInfo();
	    pageInfo.setEndPage(endPage);
	    pageInfo.setListCount(listCount);
	    pageInfo.setMaxPage(maxPage);
	    pageInfo.setPage(page);
	    pageInfo.setStartPage(startPage);   
		
	    request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("newsBean", newsBean);
		
		ActionForward forward = new ActionForward("news/list.jsp",false);
		
		return forward;

	
	}
}
