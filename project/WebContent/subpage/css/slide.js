const all = ele => document.querySelectorAll(ele)
const one = ele => document.querySelector(ele)
const slide = _ => {
  const wrap = one('.slide')
  const target = wrap.children[0]
  const len = target.children.length
  const liStyle = `
    position:absolute;
    left:0;right:0;top:0;bottom:0;transition:1s;opacity:0
  `
  target.style.cssText = `position:relative;`
  Array.from(target.children).forEach(ele => ele.style.cssText = liStyle)
  target.children[0].style.opacity = 1
  let pos = 0
  setInterval(_ => {
    target.children[pos].style.opacity = 0
    pos = (pos + 1) % len 
    target.children[pos].style.opacity = 1
  }, 3500)
}
window.onload = function () {
  slide()
}

function main(){
    window.location = "https://xiix1205.github.io/main/main.html"
}

function login(){
    window.location= "https://kana9648.github.io/Movie/login/login.html"
}

function join(){
    window.location = "https://kana9648.github.io/Movie/sign/sign.html"
}

function board_1(){
    window.location = "http://haha922.dothome.co.kr/movie_html.html"
}

function board_2(){
    window.location = "http://ieouy0605.dothome.co.kr/subpage/boardlist.html"
}

function board_3(){
    window.location ="https://kana9648.github.io/Movie/news/newsmain.html"
}

function board_4(){
    window.location ="https://xiix1205.github.io/main/location.html"
}





var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}


function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("post_view");
  if (n > slides.length){
      slideIndex = 1
  }    
  if (n < 1){
      slideIndex = slides.length
  }
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  
  slides[slideIndex-1].style.display = "block";  
  
}















































































































