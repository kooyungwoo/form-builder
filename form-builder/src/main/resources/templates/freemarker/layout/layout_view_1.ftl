<!DOCTYPE html>
<html lang="ko">

<head>
    <title>form builder</title>
      <!--[if lt IE 10]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
      <!-- Meta -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <!-- Favicon icon -->
      <link rel="icon" type="image/x-icon" href="/images/favicon.ico"/>
    	<!-- Google font-->
	  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,500"/>
      <!-- Required Fremwork -->
      <link rel="stylesheet" type="text/css" href="/css/bootstrap/css/bootstrap.min.css"/>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.css" integrity="sha512-EF5k2tHv4ShZB7zESroCVlbLaZq2n8t1i8mr32tgX0cyoHc3GfxuP7IoT8w/pD+vyoq7ye//qkFEqQao7Ofrag==" crossorigin="anonymous" referrerpolicy="no-referrer" />
      <!-- 버튼 물결 효과 waves.css -->
      <link rel="stylesheet" type="text/css" href="/pages/waves/css/waves.min.css" media="all"/>
      <!-- 아이콘 관련 themify icon -->
      <link rel="stylesheet" type="text/css" href="/icon/themify-icons/themify-icons.css"/>
      <!-- 아이콘 관련 Font Awesome -->
      <link rel="stylesheet" type="text/css" href="/icon/font-awesome/css/font-awesome.min.css"/>
      <!-- 스크롤바 디자인 변경 scrollbar.css -->
      <link rel="stylesheet" type="text/css" href="/css/jquery.mCustomScrollbar.css"/>
      <!-- 다지인된 alert -->
      <link rel="stylesheet" href="/css/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
      <!-- Style.css -->
      <link rel="stylesheet" type="text/css" href="/css/style.css"/>
      <link rel="stylesheet" type="text/css" href="/css/common.css"/>
      
      <script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/js/jquery-validation/additional-methods.min.js"></script>
	<script type="text/javascript" src="/js/jquery-validation/localization/messages_ko.min.js"></script>
	<script type="text/javascript" src="/js/jquery.form.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.js" integrity="sha512-2ABKLSEpFs5+UK1Ol+CgAVuqwBCHBA0Im0w4oRCflK/n8PUVbSv5IY7WrKIxMynss9EKLVOn1HZ8U/H2ckimWg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.ui.position.js" integrity="sha512-vBR2rismjmjzdH54bB2Gx+xSe/17U0iHpJ1gkyucuqlTeq+Q8zwL8aJDIfhQtnWMVbEKMzF00pmFjc9IPjzR7w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/3.0.1/mustache.min.js"></script>
	<!--드롭다운 구현-->
    <script type="text/javascript" src="/js/popper.js/popper.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap/js/bootstrap.min.js "></script>
	<!--전체화면플러그인-->
    <script type="text/javascript" src="/pages/widget/excanvas.js "></script>
    <!-- 버튼 물결 효과 플러그인 waves js -->
    <script type="text/javascript" src="/pages/waves/js/waves.min.js"></script>
    <!-- div 스크롤 플러그인 jquery slimscroll js -->
    <script type="text/javascript" src="/js/jquery-slimscroll/jquery.slimscroll.js"></script>
    <!-- html5 css3 지원여부 확인 플러그인 modernizr js -->
    <script type="text/javascript" src="/js/modernizr/modernizr.js "></script>
    <!-- 부드러운 스크롤 플러그인 slimscroll js -->
    <script type="text/javascript" src="/js/SmoothScroll.js"></script>
	<!--스크롤바 디자인 변경-->
    <script type="text/javascript" src="/js/jquery.mCustomScrollbar.concat.min.js "></script>
    <!-- 디자인 alert -->
    <script src="/js/sweetalert2/sweetalert2.min.js"></script>
    <!-- menu js -->
    <script type="text/javascript" src="/js/pcoded.min.js"></script>
    <script type="text/javascript" src="/js/vertical-layout.min.js "></script>
    <script type="text/javascript" src="/js/script.js "></script>
    <script type="text/javascript" src="/js/common.js "></script>
  </head>

  <body>
  <!-- Pre-loader start -->
  <div class="theme-loader">
      <div class="loader-track">
          <div class="preloader-wrapper">
              <div class="spinner-layer spinner-blue">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
              <div class="spinner-layer spinner-red">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
            
              <div class="spinner-layer spinner-yellow">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
            
              <div class="spinner-layer spinner-green">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
          </div>
      </div>
  </div>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          <nav class="navbar header-navbar pcoded-header">
              <div class="navbar-wrapper">
                  <div class="navbar-logo">
                      <a class="mobile-menu waves-effect waves-light" id="mobile-collapse" href="#!">
                          <i class="ti-menu"></i>
                      </a>
                      <div class="mobile-search waves-effect waves-light">
                          <div class="header-search">
                              <div class="main-search morphsearch-search">
                                  <div class="input-group">
                                      <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                      <input type="text" class="form-control" placeholder="Enter Keyword">
                                      <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <a href="index-work.html">
                          FORM BUILDER WORK
                      </a>
                      <a class="mobile-options waves-effect waves-light">
                          <i class="ti-more"></i>
                      </a>
                  </div>
                
                  <div class="navbar-container container-fluid">
                      <ul class="nav-left">
                          <li>
                              <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                          </li>
                          <li>
                              <a href="#!" onclick="javascript:toggleFullScreen()" class="waves-effect waves-light">
                                  <i class="ti-fullscreen"></i>
                              </a>
                          </li>
                      </ul>                      
                  </div>
              </div>
          </nav>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  <nav class="pcoded-navbar">
                      <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
                      <div class="pcoded-inner-navbar main-menu">                          
                          <div class="pcoded-navigation-label" data-i18n="nav.category.navigation">FORM BOARD</div>
                          <ul class="pcoded-item pcoded-left-item">
                              <li class="active">
                                  <a href="index.html" class="waves-effect waves-dark">
                                      <span class="pcoded-micon"><i class="ti-clipboard"></i><b>D</b></span>
                                      <span class="pcoded-mtext" data-i18n="nav.dash.main">MENU1</span>
                                      <span class="pcoded-mcaret"></span>
                                  </a>
                              </li>
							  <li>
                                  <a href="#" class="waves-effect waves-dark">
                                      <span class="pcoded-micon"><i class="ti-files"></i><b>D</b></span>
                                      <span class="pcoded-mtext" data-i18n="nav.dash.main">MENU2</span>
                                      <span class="pcoded-mcaret"></span>
                                  </a>
                              </li>                              
                          </ul>                          
                      </div>
                  </nav>
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      <div class="page-header">
                          <div class="page-block">
                              <div class="row align-items-center">
                                  <div class="col-md-8">
                                      <div class="page-header-title">
                                          <h5 class="m-b-10">menu name</h5>
                                          <p class="m-b-0">description</p>
                                      </div>
                                  </div>
                                  <div class="col-md-4">
                                      <ul class="breadcrumb-title">
                                          <li class="breadcrumb-item">
                                              <a href="index.html"> <i class="fa fa-home"></i> </a>
                                          </li>
                                          <li class="breadcrumb-item"><a href="#!">menu name</a>
                                          </li>
                                      </ul>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <#include TPL>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
