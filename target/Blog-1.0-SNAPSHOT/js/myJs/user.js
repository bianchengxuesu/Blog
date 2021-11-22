//退出系统
function loginOut() {
    layer.alert('确定退出系统吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            location.href = "/Blog/user/loginOut";
        }
    });
}



//异步更新用户信息
function updateUser(){

    var form = $("#updateUserForm").serialize();

    $.post("/Blog/user/updateUser",form,function (data) {

        if(data.ok) {
            layer.alert(data.mess, {icon: 6});
            setTimeout(function () {
                //修改成功，关闭模态窗口
                //setTimeout:隔多长时间执行指定代码
                $("#seeUserInfo").modal("hidden");
            },1000);
        }

    },"json");

}


/*
//异步更新用户信息
function updateUser() {
    //表单序列化 能够把表单的内容拼接成 key=值&key=值...,返回值是字符串
    var form = $('#updateUserForm').serialize();
    $.post("/Blog/user/updateUser",form,function (data) {
        if(data.ok){
            layer.alert(data.mess, {icon: 6});

            setTimeout(function () {
                //重新登录,跳转到登录页面
                //setTimeout:隔多长时间执行指定代码
                location.href = "../../login.jsp";
            },1000);

        }else {
            layer.alert(data.mess, "20px");
        }
    },'json');
}
*/
