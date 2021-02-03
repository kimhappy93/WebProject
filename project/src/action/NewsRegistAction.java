package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.NewsRegistService;
import vo.ActionForward;
import vo.NewsBean;

public class NewsRegistAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      NewsRegistService newsRegistService = new  NewsRegistService();
         
         String realFolder = "";
         
         String saveFolder = "/newsfile";
         String encType = "UTF-8";
         int maxSize = 1024*1024*1024;
         
         ServletContext context = request.getServletContext();
         realFolder = context.getRealPath(saveFolder);
         
         MultipartRequest multi = new MultipartRequest(request,
               realFolder, maxSize, encType,
               new DefaultFileRenamePolicy());
         
         Enumeration files = multi.getFileNames();
         
         ArrayList<String> newsInfo = new ArrayList<String>();
         ArrayList<String> newsFileName = new ArrayList<String>();
         
         
         while(files.hasMoreElements()) {
            newsInfo.add((String)files.nextElement());
         }
         
         int i = 0;
         
         for(i = newsInfo.size()-1; i >=0; i--) {
            newsFileName.add(multi.getFilesystemName(newsInfo.get(i)));
         }
         
         
//         String s1 = multi.getFilesystemName(newsInfo.get(0));
//         String s2 = multi.getFilesystemName(newsInfo.get(1));
//         String s3 = multi.getFilesystemName(newsInfo.get(2));
//         String s4 = multi.getFilesystemName(newsInfo.get(3));
//         String s5 = multi.getFilesystemName(newsInfo.get(4));
//         String s6 = multi.getFilesystemName(newsInfo.get(5));
         
         
//         newsFileName.add(s1);
//         newsFileName.add(s2);
//         newsFileName.add(s3);
//         newsFileName.add(s4);
//         newsFileName.add(s5);
//         newsFileName.add(s6);
//         
//         System.out.println(s1 + ":" + s2 + ":" + s3 + ":" + s4 + ":" + s5 + ":" + s6) ;
//          
//         Collections.sort(newsFileName);
               
         
         NewsBean newsBean = new NewsBean(
                 0, 
                 multi.getParameter("news_name"), 
                 multi.getParameter("news_subject"),
                 newsFileName.get(0),
                 multi.getParameter("content_a"),
                 newsFileName.get(1),
                 multi.getParameter("content_b"),
                 newsFileName.get(2),
                 multi.getParameter("content_c"),
                 newsFileName.get(3),
                 multi.getParameter("content_d"),
                 newsFileName.get(4),
                 multi.getParameter("content_e"),
                 newsFileName.get(5),
                 multi.getParameter("content_f"),
                 multi.getParameter("news_date"),
                 0);
         
         
         boolean isRegistSucess = newsRegistService.registNews(newsBean);
         
         ActionForward forward = null;
         
         if(isRegistSucess) {
            forward = new ActionForward("newsList.mo",true);
         }else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('뉴스등록실패');");
            out.println("history.back();");
            out.println("</script>");
         }
         
         return forward;
   }

}