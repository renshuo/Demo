// 应用控制模块
var app = require("app")

// 创建窗口模块
var BrowserWindow = require('browser-window'),

// 主窗口
var mainWindow = null;

// 初始化并准备创建主窗口
app.on('ready', function () {
    // 创建一个宽800px 高700px的窗口
    mainWindow = new BrowserWindow({
        width: 800,
        height: 700,
    });
    // 载入应用的inde.html
    mainWindow.loadUrl('file://' + __dirname + '/html/index.html');
    // 打开开发工具界面
    mainWindow.openDevTools();
    // 窗口关闭时触发
    mainWindow.on('closed', function(){
        // 想要取消窗口对象的引用， 如果你的应用支持多窗口，你需要将所有的窗口对象存储到一个数组中，然后在这里删除想对应的元素
        mainWindow = null            
    });    
});
