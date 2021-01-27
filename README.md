# 영화커뮤니티(MOVIEMOA) - 팀프로젝트

## Front-End 기능
> 더보기 버튼
```
//더보기
document.addEventListener('DOMContentLoaded', function(){ //DOM 생성 후 이벤트 리스너 등록
    //더보기 버튼 이벤트 리스너
    document.querySelector('.btn_open').addEventListener('click', function(e){
        
        let classList = document.querySelector('.detailinfo').classList; // 더보기 프레임의 클래스 정보 얻기
        let contentHeight = document.querySelector('.detailinfo > .content').offsetHeight; //컨텐츠 높이 얻기
        
        // 숨긴 내용 보이기
        if(classList.contains('showstep1')){
            classList.remove('showstep1');
            if(contentHeight > 400){
                classList.add('showstep2'); document.querySelector('.btn_open').classList.add('hide');
            }
        } 
    });
});
```

> 영화 평점 체크
```
//별점 체크
function chkchk (form){ 
    var arr_form = document.getElementsByName('m_star'); 
    var num = 0; 
    for(var i=0; i<arr_form.length; i++){ 
        if(arr_form[i].checked){ 
            num++; 
        } 
    } 
    if(!num){ 
        alert('별점을 눌러주세요.'); 
        return false; 
    } 
} 
```

> 영화 평점을 숫자로 표현
```
//별점 숫자로
var logID = 'log',
  log = $('<h3 id="'+logID+'"></h3>');
$('.rating').prepend(log);
  $('[type*="checkbox"]').change(function () {
    var me = $(this);
    log.html(me.attr('value'));
});
```

> 네티즌 리뷰 부분에 출력되는 평점
```
//숫자 별점으로
$.fn.generateStars = function() {
    return this.each(function(i,e){$(e).html($('<span/>').width($(e).text()*16));});
};

// 숫자 평점을 별로 변환하도록 호출하는 함수
$('.star-prototype').generateStars();
```

> 사진 슬라이드
```
//슬라이드
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  var captionText = document.getElementById("caption");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  captionText.innerHTML = dots[slideIndex-1].alt;
}
```

## Back-End 기능

> 영화 리스트 페이징 처리

![gif5](https://user-images.githubusercontent.com/68000697/105960754-01cb7e00-60c1-11eb-9915-cea4e5595043.gif)

> 관리자 영화등록

![gif1](https://user-images.githubusercontent.com/68000697/105953491-9aa8cc00-60b6-11eb-8993-f3784ca085b8.gif)

```
      String realFolder = ""; //서버 상의 파일 경로를 저장할 실제 경로를 저장할 변수 정의
      String saveFolder = "/moviefile"; //파일 업로드할 디렉토리
      String encType = "UTF-8";
      int maxSize = 1024*1024*1024; //업로드할 파일 사이즈
      
      ServletContext context = request.getServletContext();
      realFolder = context.getRealPath(saveFolder); //디렉토리 서버 상의 실제 경로
      
      //파일 업로드를 처리하는 MultipartRequest 객체 생성
      MultipartRequest multi = new MultipartRequest(request,
            realFolder, maxSize, encType,
            new DefaultFileRenamePolicy());
      
      ArrayList<String> movieInfo = new ArrayList<String>();
      ArrayList<String> movieFileName = new ArrayList<String>();
      
      Enumeration files = multi.getFileNames(); //iterator 예전 버전
      
      while(files.hasMoreElements()) {
    	  movieInfo.add((String)files.nextElement()); //업로드 된 파일 역순 전달
       }
       
       int i = 0;
       
       //파일이 역순으로 전달되기 때문에 반복문을 역으로 수행해서 원래의 순서대로 저장
       for(i = movieInfo.size()-1; i >=0; i--) {
    	   movieFileName.add(multi.getFilesystemName(movieInfo.get(i)));
       }
      
       Collections.sort(movieFileName); //파일명 오름차순 정렬
          
     
      //영화등록하는 비즈니스 로직이 실행되는 메소드 호출
      MovieList movieList = new MovieList(
              0, 
              multi.getParameter("m_title"), 
              movieFileName.get(0),  
              multi.getParameter("m_date"),
              movieFileName.get(1),
              multi.getParameter("m_title_eng"),
              Integer.parseInt(multi.getParameter("m_year")),
              multi.getParameter("m_nation"),
              multi.getParameter("m_age"),
              multi.getParameter("m_genre"),
              multi.getParameter("m_time"),
              multi.getParameter("m_total"),
              multi.getParameter("m_director"),
              multi.getParameter("m_actor"),
              multi.getParameter("m_summary1"),
              multi.getParameter("m_summary2"),
              multi.getParameter("m_summary3"),
              movieFileName.get(2),
              movieFileName.get(3),
              movieFileName.get(4),
              movieFileName.get(5),
              movieFileName.get(6),
              movieFileName.get(7),
              multi.getParameter("m_video"));
      
      
      boolean isRegistSucess = movieRegistService.registMovieList(movieList);
      
      ActionForward forward = null;
      
      if(isRegistSucess) {
    	 //영화등록성공시 영화리스트 보기 요청 실행
         forward = new ActionForward("movieList.mo",true);
      }else {
    	 //영화등록실패시 경고창 출력후 이전 페이지로 이동
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('영화등록실패');");
         out.println("history.back();");
         out.println("</script>");
      }
      
      return forward;
```

> 영화상세페이지 조회

![gif2](https://user-images.githubusercontent.com/68000697/105954980-e3618480-60b8-11eb-8c86-2e4d635c587a.gif)

> 회원 영화 평점 및 리뷰 등록

![gif3](https://user-images.githubusercontent.com/68000697/105955782-253efa80-60ba-11eb-9267-e84e2529378b.gif)

> 회원 영화 평점 및 리뷰 삭제

![gif4](https://user-images.githubusercontent.com/68000697/105955946-66370f00-60ba-11eb-8885-ed67e35fad21.gif)
