package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MovieBean;
import vo.PageInfo;

 public class BoardListAction implements Action {
    
    public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
       
      ArrayList<MovieBean> articleList=new ArrayList<MovieBean>();
      int page=1;
      int limit=10;
      int listCount = 0;

      String boardtype = request.getParameter("boardtype");
      
      if(request.getParameter("page")!=null){
         page=Integer.parseInt(request.getParameter("page"));
      }
      
      BoardListService boardListService = new BoardListService();
      
    //원코드
      // int listCount=boardListService.getListCount(); //�� ����Ʈ ���� �޾ƿ�.
      
      
    ///페이지처리 if
      if(boardtype.equalsIgnoreCase("BOARDFREE")) {
    	  listCount = boardListService.getFreeListCount();
      } else if(boardtype.equalsIgnoreCase("BOARDNOTICE")) {
    	  listCount = boardListService.getListCount();
      } else {
    	  listCount = boardListService.getListCount();
      }
      ////
      
    //전체 게시글 집계 변수
      int allCount = boardListService.getListCount();
      
      
   // 파라미터에 따라 다른 테이블 불러오기
      if(boardtype.equalsIgnoreCase("BOARDALL"))
      {
    	  articleList = boardListService.getAllArticleList(page,limit,boardtype);
      } else {
      
    	  articleList = boardListService.getArticleList(page,limit,boardtype);
      }

      
      //�� ������ ��.
      int maxPage=(int)((double)listCount/limit+0.95); //0.95�� ���ؼ� �ø� ó��.
         //���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
         
      int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
         //���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
         
      int endPage = startPage+10-1;

      if (endPage> maxPage) endPage= maxPage;

      
      PageInfo pageInfo = new PageInfo();
      pageInfo.setEndPage(endPage);
      pageInfo.setListCount(listCount);
      //
      pageInfo.setAllCount(allCount); //전체 게시글 집계 변수
      //
      pageInfo.setMaxPage(maxPage);
      pageInfo.setPage(page);
      pageInfo.setStartPage(startPage);   
      request.setAttribute("pageInfo", pageInfo);
      request.setAttribute("articleList", articleList);
      ActionForward forward= new ActionForward();
      forward.setPath("boardlist.jsp");
      return forward;
         
    }
    
 }
