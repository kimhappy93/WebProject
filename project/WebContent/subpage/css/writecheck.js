function check(){
        		
	        	if(document.postFrm.MOVIE_GENRE.value==""){
	        		alert("카테고리를 선택해 주세요.");
	        		
	        		return false;
	        	}
	
	        	if(document.postFrm.MOVIE_SUBJECT.value==""){
	        		alert("제목을 입력해 주세요.");
	        		document.postFrm.MOVIE_SUBJECT.focus();
	        		return false;
	        	}
	        	
	        	if(document.postFrm.MOVIE_CONTENT.value==""){
	        		alert("내용을 입력해 주세요.");
	        		document.postFrm.MOVIE_CONTENT.focus();
	        		return false;
	        	}
	        	
	        	else return true;
        	}