<%@ page contentType="text/html; charset=UTF-8" %>
<header id="header" class="header">
    <div class="header-inner">
        <div class="site-brand-wrapper">
            <div class="site-meta ">
                <div class="custom-logo-site-title">
                    <a href="/" class="brand" rel="start" style="opacity: 1;">
                        <span class="logo-line-before"><i></i></span>
                        <span class="site-title" style="opacity: 1; top: 0px;">${obj.config.blogName}</span>
                        <span class="logo-line-after"><i></i></span>
                    </a>
                </div>
                <h1 class="site-subtitle" itemprop="description"
                    style="opacity: 1; top: 0px;">${obj.host.signature}</h1>
            </div>
            <div class="site-nav-toggle">
                <button aria-label="切换导航栏">
                    <span class="btn-bar"></span>
                    <span class="btn-bar"></span>
                    <span class="btn-bar"></span>
                </button>
            </div>
        </div>
        <nav class="site-nav">
            <ul id="menu" class="menu">
                <li class="menu-item menu-item-home" style="opacity: 1; transform: translateY(0px);">
                    <a href="/" rel="section">
                        <i class="menu-item-icon fa fa-fw fa-home"></i><br>首页
                    </a>
                </li>
                <li class="menu-item menu-item-tags" style="opacity: 1; transform: translateY(0px);">
                    <a href="/label" rel="section">
                        <i class="menu-item-icon fa fa-fw fa-tags"></i><br>分类</a>
                </li>
                <li class="menu-item menu-item-archives" style="opacity: 1; transform: translateY(0px);">
                    <a href="/archives" rel="section">
                        <i class="menu-item-icon fa fa-fw fa-archive"></i><br>归档</a>
                </li>
            </ul>
        </nav>
    </div>


</header>