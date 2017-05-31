$(document).ready(function() {
	
	

	$('.block a').click(function() {
		
		var rating = $(this).html();
		var url = this.name;
		
		$.ajax({
			type : 'Get',
			url : url,
			success : function(data) {
				
				// 判断成功，在jsp页面显示评分。现在没有判断
				 $("#rating").html(rating+"分");
				
			}
		});

	});

});
