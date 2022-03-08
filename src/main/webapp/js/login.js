layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
         $ = layui.jquery;

    form.on('submit(login)',function(date){
        console.log(date)
        $.ajax({
            type:'post',
            url:'home/login',
            dataType:'json',
            data:date.field,
            success:function (res) {
                console.log(res);
                if(res.code == 0){
                    window.location.href = "home/main"
                }else{
                    layer.msg(res.msg,{icon:2})
                    changeCode()
                }
            }
        })
        return false;
    })
    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})

//点击以后切换验证码的函数
function changeCode(){
    var img = document.getElementById("codeImg")
    img.src = "home/getCode?tt="+new Date().getTime()
}
