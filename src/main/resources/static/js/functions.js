var imgs;
function onFileSelected(event) {
    $('#img').show();
    var selectedFile = event.target.files[0];
    var reader = new FileReader();
  
    var imgtag = document.getElementById("img");
    imgtag.title = selectedFile.name;
  
    reader.onload = function(event) {
      imgtag.src = event.target.result;
      imgs = event.target.result;
    };
  
    reader.readAsDataURL(selectedFile);
  }
  /*function copy() {
    var copyText = document.getElementById("link");
    copyText.select();
    copyText.setSelectionRange(0, 99999); 
    document.execCommand("copy");
    alert("Copied the text: " + copyText.value);
}*/
function uploadCheck(){
    if($('#fileUpload').val() === "")
    {
        $('#error').text('please select a file');
        return false;
    }
    $('#error').text('');
} 

function sendRegister() {
    var jsonLog = {
        email: $('#mail').val(),
        pass: $('#password').val(),
        name: $('#name').val(),
        dob: $('#datepick').val(),
        image: imgs,
    }
    registerUser(JSON.stringify(jsonLog));
}       