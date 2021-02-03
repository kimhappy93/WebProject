<%@page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="mgr" class="sign.MemberMgr" />
<jsp:useBean id="bean" class="sign.MemberBean" />
<jsp:setProperty property="*" name="bean" />
<%
	boolean result = mgr.insertMember(bean);
String msg = "회원가입에 실패 하였습니다.";
String loc = "member.jsp";
if (result) {
	System.out.println("성공");
	msg = "회원가입을 하였습니다.";
	loc = "login.jsp";
}
%>
<script>
	alert("<%=msg%>");
	location.href = "<%=loc%>";
</script>