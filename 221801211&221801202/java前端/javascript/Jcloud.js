//标签云
/*!
 * jQCloud Plugin for jQuery
 *
 * Version 1.0.4
 *
 * Copyright 2011, Luca Ongaro
 * Licensed under the MIT license.
 *
 * Date: 2013-05-09 18:54:22 +0200
*/
(function(e){
    "use strict";
    e.fn.jQCloud=function(t,n){
        var r=this,i=r.attr("id")||Math.floor(Math.random()*1e6).toString(36),
        s={
            width:r.width(),height:r.height(),center:{
                x:(n&&n.width?n.width:r.width())/2,y:(n&&n.height?n.height:r.height())/2},
                delayedMode:t.length>50,shape:!1,encodeURI:!0,removeOverflowing:!0
        };
            n=e.extend(s,n||{}),r.addClass("jqcloud").width(n.width).height(n.height),r.css("position")==="static"&&r.css("position","relative");
            var o=function(){var s=function(e,t){
                var n=function(e,t){return Math.abs(2*e.offsetLeft+e.offsetWidth-2*t.offsetLeft-t.offsetWidth)<e.offsetWidth+t.offsetWidth&&Math.abs(2*e.offsetTop+e.offsetHeight-2*t.offsetTop-t.offsetHeight)<e.offsetHeight+t.offsetHeight?!0:!1},r=0;
            for(r=0;r<t.length;r++)if(n(e,t[r]))
            return!0;
            return!1
        };
            for(var o=0;o<t.length;o++)t[o].weight=parseFloat(t[o].weight,10);t.sort(function(e,t){
                return e.weight<t.weight?1:e.weight>t.weight?-1:0
            });
            var u=n.shape==="rectangular"?18:2,a=[],f=n.width/n.height,l=function(o,l){
                var c=i+"_word_"+o,h="#"+c,p=6.28*Math.random(),d=0,v=0,m=0,g=5,y="",b="",w;
            l.html=e.extend(l.html,{id:c}),l.html&&l.html["class"]&&(y=l.html["class"],delete l.html["class"]),t[0].weight>t[t.length-1].weight&&(g=Math.round((l.weight-t[t.length-1].weight)/(t[0].weight-t[t.length-1].weight)*9)+1),w=e("<span>").attr(l.html).addClass("w"+g+" "+y),l.link?(typeof l.link=="string"&&(l.link={href:l.link}),n.encodeURI&&(l.link=e.extend(l.link,{href:encodeURI(l.link.href).replace(/'/g,"%27")})),b=e("<a>").attr(l.link).text(l.text)):b=l.text,w.append(b);if(!!l.handlers)for(var E in l.handlers)l.handlers.hasOwnProperty(E)&&typeof l.handlers[E]=="function"&&e(w).bind(E,l.handlers[E]);
            r.append(w);
            var S=w.width(),x=w.height(),T=n.center.x-S/2,N=n.center.y-x/2,C=w[0].style;C.position="absolute",C.left=T+"px",C.top=N+"px";
                while(s(w[0],a)){
                    if(n.shape==="rectangular"){
                        v++,v*u>(1+Math.floor(m/2))*u*(m%4%2===0?1:f)&&(v=0,m++);
                        switch(m%4){
                            case 1:T+=u*f+Math.random()*2;break;
                            case 2:N-=u+Math.random()*2;break;
                            case 3:T-=u*f+Math.random()*2;break;
                            case 0:N+=u+Math.random()*2
                        }
                    }
                    else 
                        d+=u,p+=(o%2===0?1:-1)*u,T=n.center.x-S/2+d*Math.cos(p)*f,N=n.center.y+d*Math.sin(p)-x/2;
                    C.left=T+"px",C.top=N+"px"
                }
            if(n.removeOverflowing&&(T<0||N<0||T+S>n.width||N+x>n.height)){
                w.remove();
                return
            }a.push(w[0]),e.isFunction(l.afterWordRender)&&l.afterWordRender.call(w)},c=function(i){
                i=i||0;if(!r.is(":visible")){setTimeout(function(){c(i)},10);return}i<t.length?(l(i,t[i]),setTimeout(function(){c(i+1)},10)):e.isFunction(n.afterCloudRender)&&n.afterCloudRender.call(r)};
            n.delayedMode?c():(e.each(t,l),e.isFunction(n.afterCloudRender)&&n.afterCloudRender.call(r))};
            return setTimeout(function(){o()},10),r}})(jQuery);