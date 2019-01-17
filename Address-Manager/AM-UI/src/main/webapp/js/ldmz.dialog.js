var bootstrapDialog = function(type,content,callback){
    if(type){
        if(type == 'success'){
            return new BootstrapDialog({
                type:BootstrapDialog.TYPE_DEFAULT,
                title: '提示',
                closable: true,
                message: content,
                size:BootstrapDialog.SIZE_SMALL,
                buttons: [{
                    label: '确定',
                    cssClass: 'btn-primary',
                    action: function(dialogRef){
                        dialogRef.close();
                        if(typeof(callback)=="function"){
                            callback();
                        }

                    }
                }]
            });
        }

        if(type == 'warning'){
            return new BootstrapDialog({
                type:BootstrapDialog.TYPE_DEFAULT,
                title: '警告',
                closable: true,
                message: content,
                size:BootstrapDialog.SIZE_SMALL,
                buttons: [{
                    label: '确定',
                    cssClass: 'btn-primary',
                    action: function(dialogRef){
                        dialogRef.close();
                    }
                }]
            });
        }if(type == 'error'){
            return new BootstrapDialog({
                type:BootstrapDialog.TYPE_DEFAULT,
                title: '错误',
                closable: true,
                message: content,
                size:BootstrapDialog.SIZE_SMALL,
                buttons: [{
                    label: '确定',
                    cssClass: 'btn-primary',
                    action: function(dialogRef){
                        dialogRef.close();
                    }
                }]
            });
        }

        if(type == 'confirm'){
            return new BootstrapDialog.confirm({
                size:BootstrapDialog.SIZE_SMALL,
                type:BootstrapDialog.TYPE_DEFAULT,
                title: '提示',
                closable: true,
                message: content,
                btnOKLabel: '确定',
                btnCancelLabel: '取消',
                size:BootstrapDialog.SIZE_SMALL,
                btnOKClass: 'btn-primary',
                callback: callback
            });
        }

        if(type == 'input'){
            return new BootstrapDialog.show({
                message: '<input type="text" class="form-control" maxlength=16>',
                size:BootstrapDialog.SIZE_SMALL,
                type:BootstrapDialog.TYPE_DEFAULT,
                title: content,
                closable: true,
                size:BootstrapDialog.SIZE_SMALL,
                buttons: [{
                    label: '确定',
                    cssClass: 'btn-primary',
                    action: function(dialog){
                        var text = dialog.getModalBody().find('input').val();
                        callback(text);
                        dialog.close();
                    }
                }, {
                    label: '取消',
                    action: function(dialog){
                        dialog.close();
                    }
                }]
            });
        }

    }
};
