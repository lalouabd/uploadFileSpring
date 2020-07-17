


function copy() {
    var copyText = document.getElementById("link");
    copyText.select();
    copyText.setSelectionRange(0, 99999); 
    document.execCommand("copy");
    alert("Copied the text: " + copyText.value);
}
function uploadCheck(){
    if($('#fileUpload').val() === "")
    {
        $('#error').text('please select a file');
        return false;
    }

    $('#error').text('');

} 
var id;
xhttp = new XMLHttpRequest();
$('#c').click(function(event){
    $('#container').show("fast")
    $('#selectorh').css('margin-left','430px')
    $('#c').css('color','rgb(212, 209, 209)')
    $('#h').css('color','white')
    $('#m').css('color','white')
})
$('#h').click(function(event){
    $('#container').hide("fast")
    $('#selectorh').css('margin-left','38px')
    $('#h').css('color','rgb(212, 209, 209)')
    $('#m').css('color','white')
    $('#c').css('color','white')
})
$('#m').click(function(event){
    $('#container').hide("fast")
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