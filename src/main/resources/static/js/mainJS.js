var id;
xhttp = new XMLHttpRequest();
function uploadCheck(){
    if($('#fileUpload').val() === "")
    {
        $('#error').text('please select a file');
        return false;
    }
    $('#error').text('');
} 
$('#c').click(function(event){
    $('#container').show("fast")
    $('#container1').hide("fast")
    $('#selectorh').css('margin-left','430px')
    $('#c').css('color','rgb(212, 209, 209)')
    $('#h').css('color','white')
    $('#m').css('color','white')
})
$('#h').click(function(event){
    $('#container').hide("fast")
    $('#container1').hide("fast")
    $('#selectorh').css('margin-left','38px')
    $('#h').css('color','rgb(212, 209, 209)')
    $('#m').css('color','white')
    $('#c').css('color','white')
})
$('#m').click(function(event){
    $('#container').hide("fast")
    $('#container1').show("fast")
    $('#selectorh').css('margin-left','218px')
    $('#m').css('color','rgb(212, 209, 209)')
    $('#h').css('color','white')
    $('#c').css('color','white')
})
$('#submit').click(function(){
    if(uploadCheck() == false)
    return;

    $('.progress-bar').show('slow');
    $('.progress-bar-fill').css('width', 0 + '%');
    $('.progress-bar-text').text(0 + '%');

    postFile()

    var post = $.post()
    $('#fileUpload').val("")
})
$(document).ready(function(){
    
})

$('#Register').click(function(){
    $('.login').hide();
    $('.registerPnl').show();
});
$('#loginback').click(function(){
    $('.registerPnl').hide();
    $('.login').show();
})
$('#showpass').click(function(){
    $('#showpass').hide()
    $('#hidepass').show()
    $('#passwordrgstr').attr('type','password')
})
$('#hidepass').click(function(){
    $('#showpass').show()
    $('#hidepass').hide()
    $('#passwordrgstr').attr('type','text')
})

$('.table-container:td').click(copy())

