var userId;
function _initHeader(data) {
    var header = new Vue({
        el:'#header',
        data:{
            user:data.user
        }
    })
}

function _initUserInfo(data) {
    var userInfo = new Vue({
        el:'#userInfo',
        data:{
            user:data.user
        }
    })
}

function _getUserData(user,reply) {
    userId = Cookies.get("userId");
    var data={ user:null};
    if(userId !=null){
        var url = '/api/user/base/'+userId;
        $.get(url,function (data) {
            _initHeader(data);
            _initUserInfo(data);
            if(user == true) _initUser(data);
            if(reply == true) _initReplyBox(data);
        });
    }else {
        _initHeader(data);
        _initUserInfo(data);
        if(user == true) _initUser(data);
        if(reply == true) _initReplyBox(data);
    }
}

function _initNewPost(data) {
    var newPost = new Vue({
        el:"#newpost",
        data:{
            newPosts: data.posts
        }
    });
}

function _getNewPosts() {
    var url = "/api/post/newPosts";
    $.get(url,function (result) {
        _initNewPost(result);
    })
}

function _loginout() {
    if(userId != null){
        var url = '/api/user/session';
        var data = {};
        $.delete(url,data,function (result) {
            if(result.resultCode == "0") {
                location.reload();
            }
        });
    }
}

if (! String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g, '');
    };
}

if (! Number.prototype.toDateTime) {
    var replaces = {
        'yyyy': function(dt) {
            return dt.getFullYear().toString();
        },
        'yy': function(dt) {
            return (dt.getFullYear() % 100).toString();
        },
        'MM': function(dt) {
            var m = dt.getMonth() + 1;
            return m < 10 ? '0' + m : m.toString();
        },
        'M': function(dt) {
            var m = dt.getMonth() + 1;
            return m.toString();
        },
        'dd': function(dt) {
            var d = dt.getDate();
            return d < 10 ? '0' + d : d.toString();
        },
        'd': function(dt) {
            var d = dt.getDate();
            return d.toString();
        },
        'hh': function(dt) {
            var h = dt.getHours();
            return h < 10 ? '0' + h : h.toString();
        },
        'h': function(dt) {
            var h = dt.getHours();
            return h.toString();
        },
        'mm': function(dt) {
            var m = dt.getMinutes();
            return m < 10 ? '0' + m : m.toString();
        },
        'm': function(dt) {
            var m = dt.getMinutes();
            return m.toString();
        },
        'ss': function(dt) {
            var s = dt.getSeconds();
            return s < 10 ? '0' + s : s.toString();
        },
        's': function(dt) {
            var s = dt.getSeconds();
            return s.toString();
        },
        'a': function(dt) {
            var h = dt.getHours();
            return h < 12 ? 'AM' : 'PM';
        }
    };
    var token = /([a-zA-Z]+)/;
    Number.prototype.toDateTime = function(format) {
        var fmt = format || 'yyyy-MM-dd hh:mm'
        var dt = new Date(this * 1000);
        var arr = fmt.split(token);
        for (var i=0; i<arr.length; i++) {
            var s = arr[i];
            if (s && s in replaces) {
                arr[i] = replaces[s](dt);
            }
        }
        return arr.join('');
    };
}
