var ioc = {
    tmpFilePool: {
        type: 'org.nutz.filepool.NutFilePool',
        // 临时文件目录，也可以配置大小，如： args : [ "uploadTmp"，1000]
        args: ["uploadTmp"]
    },
    uploadFileContext: {
        type: 'org.nutz.mvc.upload.UploadingContext',
        singleton: false,
        args: [{refer: 'tmpFilePool'}],
        fields: {
            // 是否忽略空文件, 默认为 false
            ignoreNull: true,
            // 单个文件最大尺寸(大约的值，单位为字节，即 1048576 为 1M)
            maxFileSize: 62914560,
            // 正则表达式匹配可以支持的文件名
            nameFilter: '^(.+[.])(gif|jpg|png|jpeg|ioc)$'
        }
    },
    upload: {
        type: 'org.nutz.mvc.upload.UploadAdaptor',
        singleton: false,
        args: [{refer: 'uploadFileContext'}]
    }
}