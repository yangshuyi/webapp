<style>
#navigator_accordion td{
    padding:10px;
}
#navigator_accordion tr:hover{
    background-color: #E0ECFF;
    cursor: pointer;
}
</style>
<div id="navigator_accordion" class="easyui-accordion" data-options="fit:true,border:false">
</div>
<script>
    $(function() {
        $.get(CONSTANTS.URL.LOAD_MAVIGATOR, {}, loadNavigatorCallback, "json");
    });

    function loadNavigatorCallback(data, textStatus) {
        if (!checkDataStatus(data)) {
            return;
        }

        var modules = data.object;

        for (var moduleIdx = 0; moduleIdx < modules.length; moduleIdx++) {
            var module = modules[moduleIdx];

            var content = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
            for (var itemIdx = 0; itemIdx < module.items.length; itemIdx++) {
                var item = module.items[itemIdx];
                content += "<tr onclick=\"navigator_openModule('"+item.name+"', '"+item.actionUrl+"')\"><td width=\"20px\"><image src='"+item.imageUrl+"'/></td><td><span title=\""+item.name+"\">"+item.name+"</span></td></tr>";
            }
            content += "</table>";

            $("#navigator_accordion").accordion('add', {
                title:  module.name,
                content: content,
                selected: module.selected
            })
        }
    }

    function navigator_openModule(name, url){
       openModule(name, url);
    }

</script>