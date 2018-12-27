$(document).ready(function(){

    $('.js-link').click(function(){
        var target = $(this).attr("href");
        var href = 'a[href="'+target+'"]';
        $(".mdl-tabs__tab").removeClass("is-active");
        $(".mdl-tabs__panel").removeClass("is-active");
        $(href).addClass('is-active');
        $(target).addClass("is-active");
        return false;
    });
});