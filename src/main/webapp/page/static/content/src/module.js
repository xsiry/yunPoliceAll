define(function(require, exports, module) {
  $.root_ = $('body');
  module.exports = {
    init: function() {
      this._bindUI();
      this._main();
    },
    _main: function() {
      loadURL('../apps/news.html');
    },
    _bindUI: function() {
      $.root_.off('click', '.new_detail').on('click', '.new_detail', function(e) {
        var newsid = $(e.currentTarget).data("newsid");
        console.log(newsid)
        loadURL('../apps/news_detail.html', newsid);
        e.preventDefault();
        rowobj = null;
      })
      $.root_.off('click', '.go_back_btn').on('click', '.go_back_btn', function(e) {
        loadURL('../apps/news.html');
        e.preventDefault();
        rowobj = null;
      })
    }
  };

  function loadURL(a, newsid) {
    var b = $('div.content');
    $.ajax({
      "type": "GET",
      "url": a,
      "dataType": "html",
      "cache": !0,
      "beforeSend": function() {
        b.removeData().html(""),
          b.html('<div class="dropload-load"><span class="loading"></span>加载中...</div>'),
          b[0] == $("#content")[0] && ($("body").find("> *").filter(":not(" + ignore_key_elms + ")").empty().remove(),
            drawBreadCrumb(),
            $("html").animate({
              "scrollTop": 0
            }, "fast"))
      },
      "success": function(a) {
        b.css({
            "opacity": "0.0"
          }).html(a).append('<input type="hidden" name="newsid" value="'+ newsid +'"/>').delay(50).animate({
            "opacity": "1.0"
          }, 300),
          a = null,
          b = null
      },
      "error": function(c, d, e) {
        b.html('<h4 class="ajax-loading-error"><i class="fa fa-warning txt-color-orangeDark"></i> Error requesting <span class="txt-color-red">' + a + "</span>: " + c.status + ' <span style="text-transform: capitalize;">' + e + "</span></h4>")
      },
      "async": !0
    })
  }
})
