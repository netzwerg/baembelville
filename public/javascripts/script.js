$(document).ready(function() {

    $('a[name=modal], tr[name=modal]').click(function(e) {
        // Cancel the link behavior
        e.preventDefault();
        // Get the A tag
        var id = $(this).attr('href');

        // Get the screen height and width
        var maskHeight = $(window).height();
        var maskWidth = $(window).width();

        // Set height and width to mask to fill up the whole screen
        $('#mask').css({'top': 0, 'width':maskWidth, 'height':maskHeight});

        // transition effect
        $('#mask').fadeIn(10);
        $('#mask').fadeTo("slow", 0.8);

        // Get the window height and width
        var winH = $(window).height();
        var winW = $(window).width();

        // Set the popup window to center
        $(id).css('top', winH / 2 - $(id).height() / 2);
        $(id).css('left', winW / 2 - $(id).width() / 2);

        // transition effect
        $(id).fadeIn(200);

    });

    $('.window .close').click(function(e) {
        //Cancel the link behavior
        e.preventDefault();
        $('#mask, .window').hide();
    });

    $('tr').hover(function () {
        $(this).toggleClass('hovered');
    });

});