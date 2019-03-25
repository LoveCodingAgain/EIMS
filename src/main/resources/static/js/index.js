var new_element1=document.createElement("script");
new_element1.setAttribute("type","text/javascript");
new_element1.setAttribute("src","libs/md5.min.js");
document.body.appendChild(new_element1);
var menuItem = Vue.extend({
    name: 'menu-item',
    props:{item:{}},
    template:[
        '<li>',
        '	<a v-if="item.type === 0" href="javascript:;">',
        '		<i v-if="item.icon != null" :class="item.icon"></i>',
        '		<span>{{item.name}}</span>',
        '		<i class="fa fa-angle-left pull-right"></i>',
        '	</a>',
        '	<ul v-if="item.type === 0" class="treeview-menu">',
        '		<menu-item :item="item" v-for="item in item.list"></menu-item>',
        '	</ul>',

        '	<a v-if="item.type === 1 && item.parentId === 0" :href="\'#\'+item.url">',
        '		<i v-if="item.icon != null" :class="item.icon"></i>',
        '		<span>{{item.name}}</span>',
        '	</a>',

        '	<a v-if="item.type === 1 && item.parentId != 0" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
        '</li>'
    ].join('')
});

//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 154);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		menuList:{},
		main:"main.html",
		password:'',
		newPassword:'',
        navTitle:"项目介绍"
	},
	methods: {
		getMenuList: function () {
			$.getJSON("sys/menu/nav?_"+$.now(), function(r){
				vm.menuList = r.menuList;
			});
		},
		getUser: function(){
			$.getJSON("sys/user/info?_"+$.now(), function(r){
				vm.user = r.user;
			});
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					// 校验新旧密码不能为空.
					if(vm.password==''||vm.password==null){
                        layer.open({
                            type: 0,
                            title: '修改密码错误提示!',
                            content: '原始密码不能为空哟!'
                        });
                        return ;
					}
					// 校验旧密码不能为空.
					if((vm.password!=''||vm.password!=null)&&(vm.newPassword==''||vm.newPassword==null)){
                        layer.open({
                            type: 0,
                            title: '修改密码错误提示!',
                            content: '新密码不能为空哟!'
                        });
                        return ;
					}
                    // 校验旧密码长度,不小于6位
                    if(vm.password.length<6){
                        layer.open({
                            type: 0,
                            title: '修改密码错误提示!',
                            content: '旧密码长度至少6位哟!'
                        });
                        return ;
                    }
					// 校验新密码长度,不小于6位
					if(vm.newPassword.length<6){
                        layer.open({
                            type: 0,
                            title: '修改密码错误提示!',
                            content: '新密码长度至少6位哟!'
                        });
                        return ;
					}
					// 完成MD5的新旧密码加密.
                    var salt="shiro_password_salt";
                    vm.password=""+salt.charAt(2)+salt.charAt(7) + vm.password +salt.charAt(10) + salt.charAt(18);
                    vm.newPassword=""+salt.charAt(2)+salt.charAt(7) + vm.newPassword +salt.charAt(10) + salt.charAt(18);
                    vm.password=md5(vm.password);
                    vm.newPassword=md5(vm.newPassword);
					$.ajax({
						type: "POST",
					    url: "sys/user/password",
					    data: JSON.stringify({"password":vm.password,"newPassword":vm.newPassword}),
                        contentType: "application/json; charset=utf-8",
					    dataType: "json",
					    success: function(result){
							if(result.code == 0){
								layer.close(index);
								layer.alert('恭喜,修改成功!', function(index){
									location.reload();
								});
							}else{
                                layer.open({
                                    type: 0,
                                    title: '修改密码错误提示!',
                                    content: result.msg
                                });
                                vm.password='';
                                vm.newPassword='';
							}
						}
					});
	            }
			});
		}
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});



function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
