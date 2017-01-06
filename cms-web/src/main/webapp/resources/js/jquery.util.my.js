(function($){


    $.fixedTopicWidth = function(str,opts){
        var settings = $.extend({
            length:10,
            fill:null,
            symbolLength:6
        },opts||{});
        var pos = settings.length - str.length;
        if(pos>0){
            return str;
        }else{
            var fs = ""
            if(settings.fill){
                for(var i=0;i<settings.symbolLength;i++){
                    fs += settings.fill
                }
                return str.substr(0,settings.length-3) + fs;
            }else{
                return str.substr(0,settings.length);
            }
        }
    }

    $.fn.fixedWidth = function(opts){
        console.log(opts)
        this.each(function(){
            $(this).html($.fixedTopicWidth($(this).html()),opts)
        })


        //var settings = $.extend({
        //    length:10,
        //    fill:null,
        //    symbolLength:4
        //},opts||{});
        //this.each(function(){
        //    var text = $(this).text();
        //    console.log(text)
        //    var pos = settings.length - text.length;
        //    if(pos>0){
        //        $(this).text(text);
        //
        //    }else{
        //        var fs = "";
        //        if(settings.fill){
        //            for(var i=0;i<settings.symbolLength;i++){
        //                fs += settings.fill
        //            }
        //            $(this).text(text.substr(0,settings.length-3) + fs);
        //        }else{
        //            $(this).text(text.substr(0,settings.length));
        //        }
        //    }
        //})
        //return this
    }

    $.fn.setReadonly = function(state){
        return this.filter('input:text').attr('readonly',state);
    }


})(jQuery);