//粒子背景特效
$('body').particleground({
    dotColor: '#E8DFE8',
    lineColor: '#1b3273'
});

//进入页面先发送请求，加载验证码
code();

//向后台发送生成验证码的请求
function code() {
    $.get("/Blog/code",function (data) {
        showCheck(data);
    },'json');
}

//canvas绘制图片验证码
function showCheck(code) {
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.clearRect(0, 0, 1000, 1000);
    ctx.font = "80px 'Hiragino Sans GB'";
    ctx.fillStyle = "#E8DFE8";
    ctx.fillText(code, 0, 100);
}



//验证该填写的信息是否为空
$("#loginBtn").click(function () {

    //判断用户名是否为空
    if($("#username").val().length == 0){
        errorAlert("用户名不能为空");
        return;
    }

    //判断密码是否为空
    if($("#password").val().length == 0){
        errorAlert("密码不能为空");
        return;
    }

    //判断验证码是否为空
    if($("#code").val().length == 0){
        errorAlert("验证码不能为空");
        return;
    }


    $('.login').addClass('test'); //倾斜特效
    setTimeout(function () {
        $('.login').addClass('testtwo'); //平移特效
    }, 300);
    setTimeout(function () {
        $('.authent').show().animate({right: -320}, {
            easing: 'easeOutQuint',
            duration: 600,
            queue: false
        });
        $('.authent').animate({opacity: 1}, {
            duration: 200,
            queue: false
        }).addClass('visible');
    }, 500);


    //异步判断验证码是否正确
    $.post("/Blog/back/user/login",{
        "username":$("#username").val(),
        "password":$("#password").val(),
        "code":$("#code").val(),
    },function (data) {

        //将特效移除
        setTimeout(function () {
            $('.authent').show().animate({right: 90}, {
                easing: 'easeOutQuint',
                duration: 600,
                queue: false
            });
            $('.authent').animate({opacity: 0}, {
                duration: 200,
                queue: false
            }).addClass('visible');
            $('.login').removeClass('testtwo'); //平移特效
            $('.authent').hide();
        }, 2000);

        if (!data.ok){

            //失败后再次请求验证码等信息
            $('#code').val("");
            setTimeout(function () {
                $('.login').removeClass('test');
                errorAlert(data.mess);
                //登录失败，再次发送验证码
                $.get("/blog/code",function (data) {
                    showCheck(data);
                },'json');
            }, 2000);

        }else {
            setTimeout(function () {
                $('.login').fadeOut(100);
                //登录校验成功，跳转到后台首页
                location.href = "/Blog/workbench/index";
            }, 1000);
        }

    },'json');

});

//抽取一个弹窗函数
function errorAlert(message) {

    layer.msg(message,{offset:"20px"});
    
}