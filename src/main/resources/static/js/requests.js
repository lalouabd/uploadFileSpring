
function postFile() {
    var fd = new FormData();
    var files = $('#fileUpload')[0].files[0];
    fd.append('file', files);
    
    $.ajax({
        xhr: function(){
            var xhr = new window.XMLHttpRequest();

            // Upload progress
            xhr.upload.addEventListener("progress", function(evt){
                if (evt.lengthComputable) {
                    var percentComplete = evt.loaded / evt.total;

                    $('.progress-bar-fill').css('width', percentComplete * 100 + '%');
                    $('.progress-bar-text').text(percentComplete * 100 + '%');
                    
                    console.log(percentComplete * 100);
                }
            }, false);
            return xhr;
        },
        url:  json.url + '/api/files',
        type: 'POST',
        data: fd,
        crossDomain: true,
        contentType: false,
        processData: false,
        headers: {  'Access-Control-Allow-Origin': json.url },
        error: function(xhr, status, error) {
            // Display errors
            console.log('ERROR !');
        },
        success: function(data, textStatus, xhr) {
            $.ajax({
                url:  json.url+'/api/files/'+data.id,
                type: 'GET',
                headers: {  'Access-Control-Allow-Origin': json.url },
                success: function(datas, textStatuss, xhrs){
                    $('#container').append('<input type="text" value="'+datas.link+'" id="link"/><br></br><button onclick="copy()">click to copy link</button><br></br>');
                }
            })
        },
    })
}

function registerUser(json)
{
    const jsons = {
        "url": "https://c8a1751c94f4.ngrok.io"
    }
    $.ajax({
        url: 'https://c8a1751c94f4.ngrok.io'+ '/auth/register',
        crossDomain: true,
        type: 'POST',
        headers: {  'Access-Control-Allow-Origin': json.url },
        contentType: 'application/json',
        data: json, 
        success: function(data, textStatus, xhr){
            console.log('success');
        },
        error: function(){
            console.log('Error');
        }
    })
}