$(function(){
	$('#tabs').tabs({add: addEventHandler});  //给tabs块绑定addEventHandler事件
	$(".a_left").click(function(){
		addTab(this.href, $(this).text());
		return false;
	});
});

var tabCounter = 1;
function addTab(src, tabName) {
  if (tabCounter > 8) {
    alert('tabs can not more than 8!');
    return;
  }
  $.get(src, function(data){
	  var $div = $('<div id="new-tab-' + tabCounter + '"></div>');
	  $div.html(data).appendTo('#tabs');
	  $('#tabs').tabs("add", "#new-tab-" + tabCounter, tabName);
	  tabCounter++;
  });
}
function addEventHandler(event, ui) {
  var li = $(ui.tab).parent();
  $('<img src="img/close.png"/>') //关闭按钮
          .appendTo(li).hover(function () {
            var img = $(this);
            img.attr('src', 'img/close_hover2.png');
          }, function () {
            var img = $(this);
            img.attr('src', 'img/close.png');
          }).click(function () {        //关闭按钮,关闭事件绑定
            var li = $(ui.tab).parent();
            var index = $('#tabs li').index(li.get(0));
            $("#tabs").tabs("remove", index);
            tabCounter--;
          });
  $(ui.tab).dblclick(function () {   //双击关闭事件绑定
    var li = $(ui.tab).parent();
    var index = $('#tabs li').index(li.get(0));
    $("#tabs").tabs("remove", index);
    tabCounter--;
  });
};