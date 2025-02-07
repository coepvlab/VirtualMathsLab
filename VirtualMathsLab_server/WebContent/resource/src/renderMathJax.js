(function(MJ) {

MJ.renderMathJax = function() {
	
	 var _gaq = _gaq || [];

	   _gaq.push(['_setAccount', 'UA-15609829-1']);
	   _gaq.push(['_addDevId', 'i9k95']); // Google Analyticator App ID with Google
	   _gaq.push(['_gat._anonymizeIp']);
	   _gaq.push(['_trackPageview']);

	   (function () {
	     var ga = document.createElement('script');
	     ga.type = 'text/javascript';
	     ga.async = true;
	     ga.src =  'resource/js/ga.js';
	     var s = document.getElementsByTagName('script')[0];
	     s.parentNode.insertBefore(ga, s);
	   })();

	   window.MathJax = {
	     loader: {load: ['input/asciimath']},
	     startup: {
	       pageReady: function () {
	         //
	         // Synchronize menu renderer item with on-screen popup menu
	         //

	         return MathJax.startup.defaultPageReady();
	       }
	     },
	     tex: {
	       inlineMath: [['$', '$'], ['\\(', '\\)']],
	       processEscapes: true
	     }
	   };

	   var followHash = function () {
	     var anchor = document.querySelector('a[href="' + location.hash + '"]');
	     var permissibleTargets = [
	       "#epub",
	       "#training",
	       "#consultancy",
	       "#demo",
	       "#a11y",
	       "#samples",
	       "#ams-stub",
	       "#siam-stub",
	       "#stackoverflow-stub",
	       "#ieee-stub",
	       "#elsevier-stub",
	       "#sponsorship-program",
	       "#gettingstarted",
	       "#apis",
	       "#browsers",
	       "#to-a11y",
	       "#to-ams-stub",
	       "#to-apis",
	       "#to-browsers",
	       "#to-consultancy",
	       "#to-demo",
	       "#to-elsevier-stub",
	       "#to-epub",
	       "#to-gettingstarted",
	       "#to-ieee-stub",
	       '#to-installnow',
	       "#to-samples",
	       "#to-siam-stub",
	       "#to-sponsorship-program",
	       "#to-stackoverflow-stub",
	       "#to-training"
	     ];
	     // console.log(permissibleTargets.indexOf(location.hash));
	     if (anchor && permissibleTargets.indexOf(location.hash) > -1) {
	       anchor.click();
	       // scroll a little to offset fade-out
	       // HACK Firefox requires small timeout; not sure why
	       setTimeout(function () {
	         var h = Math.max(
	           document.documentElement.clientHeight,
	           window.innerHeight || 0
	         );
	         var offset = h / 3;
	         // console.log(offset);
	         var target =
	             document.getElementById(location.hash.slice(1)) ||
	             document.getElementById("art");
	         document.body.scrollTop += -offset;
	         document.documentElement.scrollTop += -offset;
	       }, 1);
	     }
	   };
	   window.onhashchange = followHash;

	   //
	   //  Load MathJax
	   //
	   var script = document.createElement('script');
//	   script.src = 'https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js';
	   script.src = 'resource/js/tex-mml-chtml.js';
	   script.setAttribute('id', 'MathJax-script');
	   document.head.appendChild(script);
}

})(com.coep.test.mathJax);