package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MovieViewService;
import vo.ActionForward;
import vo.MovieList;

public class MovieViewAction implements Action {

	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieViewService movieViewService = new MovieViewService();
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		MovieList movieList = movieViewService.getMovieView(m_id);
		request.setAttribute("movieList", movieList);
		ActionForward forward = new ActionForward("movieView.jsp", false);
		return forward;
	}

}
