package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NewsViewService;
import vo.ActionForward;
import vo.NewsBean;

public class NewsViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NewsViewService newsViewService = new NewsViewService();
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		NewsBean newsBean = newsViewService.getNewsView(news_num);
		request.setAttribute("newsBean", newsBean);
		ActionForward forward = new ActionForward("/news/newsView.jsp", false);
		return forward;
		
	}

}
