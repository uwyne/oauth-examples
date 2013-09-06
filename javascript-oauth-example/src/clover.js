var config = {
    clientId: 'XXXXXXXXXXXXXX',
    domain: 'https://clover.com/'
};

function getAccessToken() {
 userInfo = {};
 params = window.location.hash.split('&');
 var i = 0;
 while (param = params[i++]) {
   param = param.split("=");
   userInfo[param[0]] = param[1];
 }
}

function redirect() {
    var redirect = window.location.href.replace(window.location.hash, '');
    var url = config.domain + 'oauth/authorize?response_type=token&client_id=' + config.clientId +
        '&redirect_uri=' + encodeURIComponent(redirect);
    window.location.href = url;
}
