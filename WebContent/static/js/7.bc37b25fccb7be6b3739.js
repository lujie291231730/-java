webpackJsonp([7],{WRB9:function(module,__webpack_exports__,__webpack_require__){"use strict";Object.defineProperty(__webpack_exports__,"__esModule",{value:!0});var syscontainer={name:"syscontainer",components:{pagetitle:__webpack_require__("FNjg").a},data:function(){return{pagetitle:"default"}},watch:{$route:function(to,from){this.pagetitle=to.meta.title}},mounted:function(){this.pagetitle=this.$route.meta.title}},sys_syscontainer={render:function(){var _h=this.$createElement,_c=this._self._c||_h;return _c("div",{staticClass:"syscontainer"},[_c("pagetitle",{attrs:{pagetitle:this.pagetitle}}),this._v(" "),_c("router-view")],1)},staticRenderFns:[]};var Component=__webpack_require__("VU/8")(syscontainer,sys_syscontainer,!1,function(ssrContext){__webpack_require__("i6Lq")},"data-v-40341a7e",null);__webpack_exports__.default=Component.exports},i6Lq:function(module,exports){}});