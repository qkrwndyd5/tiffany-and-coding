<%@page import="com.itwill.shop.product.Product"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
		
		ProductService productService = new ProductService();
		String keyword = request.getParameter("mainsearchkeyword");
		String type_noStr = request.getParameter("type_no");
		String sort_option = request.getParameter("sort_option");
		List<Product> productList = new ArrayList<Product>();

		if(!type_noStr.equals("null")){
			if(keyword.equals("null")){
				if(sort_option.equals("sort_asc")){
					productList = productService.categorySortAsc(Integer.parseInt(type_noStr));
				}else if(sort_option.equals("sort_desc")){
					productList = productService.categorySortDesc(Integer.parseInt(type_noStr));
				}else{
					productList = productService.searchCaNo(Integer.parseInt(type_noStr));
				}
			}
		}else if(type_noStr.equals("null")){
			if(!keyword.equals("null")){
				if(sort_option.equals("sort_asc")){
					productList = productService.searchSortAsc(keyword);
				}else if(sort_option.equals("sort_desc")){
					productList = productService.searchSortDesc(keyword);
				}else{
					productList = productService.searchProductName(keyword);
				}		
			}else if(keyword.equals("null")){
				if(sort_option.equals("sort_asc")){
					productList = productService.priceSortAsc();
				}else if(sort_option.equals("sort_desc")){
					productList = productService.priceSortDesc();
				}else{
					productList = productService.productList();
				}		
			}
		} 
 
		
	%>
<%
boolean isLogin = false;
if (session.getAttribute("sUserId") != null) {
	isLogin = true;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Tiffany&coding</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
<script type="text/javascript">
	function product_sort(){
		document.cart_view_form.method='POST';
		document.cart_view_form.action='product_sort_action.jsp';
		document.cart_view_form.submit();
	} 
</script> 
<style type="text/css" media="screen">
</style>
	<!-- mouse effect start -->
	<jsp:include page="include_mouseffect.jsp"/>
	<!-- mouse effect end -->
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp" />
			<!-- include_common_top.jsp end-->
		</div>
		<!-- header end -->
		<!-- navigation start-->
		<div id="navigation">
		</div>
		<!-- navigation end-->
		<!-- wrapper start -->
		<div id="wrapper">
			<!-- content start -->

			<!-- include_content.jsp start-->
			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>product</b></td>
								</tr>
							</table>
							<form name = "product_sort_action_form" action="product_sort_action.jsp" method="get">
							<input type="hidden" name="type_no" value="<%=type_noStr%>">
							<input type="hidden" name="mainsearchkeyword" value="<%=keyword%>">
							<!-- 
							 <input type=text name="cart_qty" value=1 size=4 class=TXTFLD>  
							-->
							<br><b>정렬</b>&nbsp;
							<select name="sort_option" onchange="product_sort_action_form.submit();">
								<option value="select">선택
								<option value="sort_asc">가격 오름차순
								<option value="sort_desc">가격 내림차순
							</select> <br><br> 
							</form>
							<div id="f">
								<table width="100%" align="center" border="0" cellpadding="10"
									cellspacing="15" >
									<%
									int product_size=productList.size();
									int product_column_size=4;
									int product_line_count = 1;
									
									
									for (int i=0;i<productList.size();i++) {
											Product product=productList.get(i);
									%>
									<!--상품시작 -->
									<%
									 if(i%product_column_size==0){
									%>
									<tr>
									<%} %>
										
										<td align="center" width="25%"  bgcolor="ffffff"><a
											href="product_detail.jsp?p_no=<%=product.getP_no()%>"><img width="200px" height="200px"
												src="image/<%=product.getP_image()%>" border="0"></a><br />
											
											<br /> <b><%=product.getP_name()%></b>
											<form style="display: inline;">
												<input type="hidden" name="p_no" value="<%=product.getP_no()%>">
											</form><br> <font color=black>₩<%=new DecimalFormat("#,##0").format(product.getP_price())%>
										</font></td>
										
									<%if(i%product_column_size==3){%>
									</tr>
									<%} %>	
									
								   <!--상품 끝 -->
								   <%}%>	
								</table>
							</div> <br /></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp" />
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>