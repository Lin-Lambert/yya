<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:include page="/common/head.jsp" />
<title>商品列表</title>
</head>
<body class="pos-r">
<div>
    <nav class="breadcrumb">
        <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
        会员管理 <span class="c-gray en">&gt;</span> 会员列表 <a
            class="btn btn-success radius r"
            style="line-height: 1.6em; margin-top: 3px"
            href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
    <div class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<a href="javascript:;" onclick="user_dels()"
                   class="btn btn-danger radius">
					<i class="Hui-iconfont">&#xe6e2;</i>批量删除
				</a>
				<%--<a class="btn btn-primary radius" data-href="" data-title="商品管理" href="javascript:void(0)">
						<i class="Hui-iconfont">&#xe60c;</i> 编辑商品
				</a>--%>
				</span>
        </div>
        <div id="testIframe" class="mt-20">
            <table
                    class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr class="text-c">
                    <th><input type="checkbox" class="checkall" /></th>
                    <th width="80px">用户ID</th>
                    <th >用户名</th>
                    <th >联系方式</th>
                    <th >email</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="/lib/datatables/jquery.jeditable.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

    //初始化商品分类菜单树
    $(document).ready(function() {
        refreshDataTable(null);
    });

    var vcid = null;
    var refreshDataTable=function(cid) {
        vcid=cid;
        $.ajax({
            'type':'GET',
            'url':'/restful/page/user/',
            "dataType" : "json",
            'data':{'cid':vcid},
            'success':function (resp) {
                $('.table-sort').dataTable({
                    'destroy':true,
                    'data':resp,
                    'columns':[
                        {
                            "sClass" : "text-c",
                            "data": "id",
                            "bSortable": false,
                            "render": function (data, type, full, meta) {
                                return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                            }
                        },
                        {data:'id'},
                        {data:'username'},
                        {data:'phone'},
                        {data:'email'}
                    ]
                })
            }
        });
//        //重新构建table
//        $('.table-sort').dataTable().fnClearTable();
//        $('.table-sort').dataTable().fnDestroy();
//
//        var table = $('.table-sort').dataTable({
//            "bProcessing" : false, //是否显示加载
//            "sAjaxSource" : '/restful/page/user/', //请求资源路径
//            "serverSide" : true, //开启服务器处理模式
//            "destroy" : true, //重新加载表格
//            /*
//            使用ajax，在服务端处理数据
//            sSource:即是"sAjaxSource"
//            aoData:要传递到服务端的参数
//            fnCallback:处理返回数据的回调函数
//             */
//            "fnServerData" : retrieveData,
//
//            "columns" : [
//                {
//                    "sClass" : "text-c",
//                    "data": "id",
//                    "render": function (data, type, full, meta) {
//                        return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
//                    },
//                    "bSortable": false
//                },
//                {data : "id"},
//                {data : "username"},
//                {data : "phone"},
//                {data : "email"},
//            ]
//        });
    }
    function retrieveData(sSource, aoData, fnCallback) {
        $.ajax({
            'type' : 'GET',
            "url" : sSource,
            "dataType" : "json",
            "data" : {
                "aodata" : JSON.stringify(aoData),
                "cid" : vcid
            },
            "success" : function(resp) {
                fnCallback(resp);
            }
        });
    }
    //查询分类下商品
    function findProduct(cid) {
        vcid=cid;
        refreshDataTable(cid);
    }

    /*商品批量删除*/
    function user_dels(){
        layer.confirm('确认要删除选中用户吗？',function(index){
            //取出所有选中
            var ids  = $('input:checkbox:checked').map(function(){
                if($(this).val()!="on"){
                    return $(this).val();
                };
            }).get().join(",");
            if(!ids){
                layer.msg('没有选择用户!',{icon:2,time:1000});
                return ;
            }

            $.ajax({
                type: 'post',
                url: '/restful/page/user',
                dataType: 'json',
                data:{"ids":ids,"_method":"DELETE"},
                success: function(data){
                    refreshDataTable(null);
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }


</script>
</body>
</html>