$(document).ready(function(){
    $('.categoryTab > li > a').on('click', function(){
        $('.categoryTab > li > a').removeClass('categoryOn');
        $(this).addClass('categoryOn');
    });
    
    //alert("e3e");
    
//    var string1 = "잡담";
//    
//    if($(".genre_category") == "잡담"){
//        $(".genretitle").css("background","#b386e3");
//    };
});