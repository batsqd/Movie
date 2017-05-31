<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<frameset rows="10%,*" frameborder="no">
	    <frame src='http://yuanzhiyuan-pc:8080/Movie/top.action' noresize/>
		<frameset cols="20%,*">
		<frame src='http://yuanzhiyuan-pc:8080/Movie/left.action'  noresize/>
		<!-- 默认用户登录后跳到按照Romance页，并按照Romance展现数据 -->
		<frame src='http://yuanzhiyuan-pc:8080/Movie/romance.action?type=byGenres&genres=romance' name='right'/>
		</frameset>
</frameset>
</html>

