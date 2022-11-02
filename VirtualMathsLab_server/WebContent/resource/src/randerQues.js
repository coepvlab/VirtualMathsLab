

/*  var input = document.getElementById('Question');
var output = document.getElementById('randeredQue');

output.innerHTML = input.value.trim();
window.typesetInput = function (button) {
  button.disabled = true;
  output.innerHTML = input.value.trim();
  MathJax.texReset();
  MathJax.typesetClear();
  MathJax.typesetPromise([output]).catch(function (err) {
    output.innerHTML = '';
    output.appendChild(document.createTextNode(err.message));
    console.error(err);
  }).then(function () {
    button.disabled = false;
  });
 

} */
 
var _gaq = _gaq || [];

_gaq.push(['_setAccount', 'UA-15609829-1']);
_gaq.push(['_addDevId', 'i9k95']); // Google Analyticator App ID with Google
_gaq.push(['_gat._anonymizeIp']);
_gaq.push(['_trackPageview']);

(function () {
  var ga = document.createElement('script');
  ga.type = 'text/javascript';
  ga.async = true;
 // ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
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
     // var renderer = MathJax.startup.document.menu.settings.renderer;
      //var select = document.getElementById('Renderer');
     // var item = MathJax.startup.document.menu.menu.getPool().lookup('renderer');
     // select.value = renderer;
    //  if (renderer !== 'CHTML') item.setValue(renderer);
     // item.registerCallback(function () {
    //    var value = item.getValue();
    //    if (value !== select.value) select.value = value;
   //   });
  //    window.setMode = function (renderer) {
  //      if (item.getValue() !== renderer) item.setValue(renderer);
  //    }
      //
      //  Set up processing of input content
      //
    
//      output.innerHTML = input.value.trim();
      window.typesetInput = function (button) {
    	  var input = document.getElementById('MathInput');
    	  
          var output = document.getElementById('MathPreview');
         
          
      //  button.disabled = true;
        output.innerHTML = input.value.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output]).catch(function (err) {
          output.innerHTML = '';
          output.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputAct = function (button) {
    	  $("#MathPreview").html('');
    	  $(".modal-title").html(' Question in Latex Format');
    	  var input1 = document.getElementsByClassName("ActQuestion")[0].value
    	  
          var output1 = document.getElementById('MathPreview2');
         
          
      //  button.disabled = true;
        output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      
      window.typesetInputActModify = function (button) {
    	  $("#MathPreview2").html('');
    	  $(".modal-title").html(' Question in Latex Format');
    	  var input1 = document.getElementsByClassName("ModifyQuestion")[0].value
    	  
          var output1 = document.getElementById('MathPreview2');
         
          
      //  button.disabled = true;
        output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputModifyQueSol = function (button) {
    	  $("#MathPreview2").html('');
    	  $(".modal-title").html(' Solution in Latex Format');
    	  var input1 = document.getElementsByClassName("ModifyQueSolLatex")[0].value
    	  
          var output1 = document.getElementById('MathPreview2');
         
          
      //  button.disabled = true;
        output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputAddQueSol = function (button) {
    	  $("#MathPreview2").html('');
    	  $(".modal-title").html(' Solution in Latex Format');
    	  var input1 = document.getElementsByClassName("AddQueSolLatex")[0].value
    	  
          var output1 = document.getElementById('MathPreview2');
         
          
      //  button.disabled = true;
        output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForSCA1 = function (button) {
    	  $("#MathPreviewCorrectAns1").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("scaId1");
    	  
          var output1 = document.getElementById('MathPreviewCorrectAns1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.value.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForSICA1 = function (button) {
    	  $("#MathPreviewCorrectAns1").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("sicaId1");
    	  
          var output1 = document.getElementById('MathPreviewCorrectAns1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.value.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForOptionalSICA1 = function (button,i) {
    	  $("#MathPreviewWrongAns1").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("sicaId"+i);
    	  
          var output1 = document.getElementById('MathPreviewWrongAns1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.value.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      
      window.typesetInputForCMofifyOptionalSICA1 = function (button,i) {
    	  $("#MathPreview").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("scaId"+i).value;
    	  
          var output1 = document.getElementById('MathPreview');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForWMofifyOptionalSICA1 = function (button,i) {
    	 $("#MathPreview").html('');
    	 $(".modal-title").html(' Answer Latex Format');
    	  var inputi = document.getElementById("sicaId"+i).value;
    	  
          var outputi = document.getElementById('MathPreview');
         
          
      //  button.disabled = true;
          outputi.innerHTML = inputi.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([outputi]).catch(function (err) {
          outputi.innerHTML = '';
          outputi.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });
    		  
      }
 //   add que model for multiple Correct Answer start
      
      window.typesetInputForAddMCA1 = function (button) {
    	  $("#MathPreviewMCA").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("mcaId1").value;
    	  
          var output1 = document.getElementById('MathPreviewMCA');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForAddMICA1 = function (button) {
    	  $("#MathPreviewMCA").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("micaId1").value;
    	  
          var output1 = document.getElementById('MathPreviewMCA');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForMCA1 = function (button, i) {
    	  $("#MathPreviewMCAAns1").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  
    	  var input1 = document.getElementById("mcaId" + i).value;
    	  
          var output1 = document.getElementById('MathPreviewMCAAns1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForMICA1 = function (button,i) {
    	  $("#MathPrevieMCAwWrongAns1").html('');
    	  $(".modal-title").html('Answer Latex Format');
    	 
    	  var input1 = document.getElementById("micaId" + i).value;
    	  
          var output1 = document.getElementById('MathPrevieMCAwWrongAns1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForOptionalMICA1 = function (button,i) {
    	  $("#MathPreviewWrongAns1").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("mtpId"+i).value;
    	  
          var output1 = document.getElementById('MathPreviewWrongAns1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForCMofifyOptionalMCA1 = function (button,i) {
    	  $("#MathPreview").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("mcaId"+i).value;
    	  
          var output1 = document.getElementById('MathPreview');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForWMofifyOptionalMCA1 = function (button,i) {
    	  $("#MathPreview").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("micaId"+i).value;
    	  
          var output1 = document.getElementById('MathPreview');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
 //   add que model for multiple Correct Answer end
      window.typesetInputForFIB1 = function (button) {
    	  $("#MathPreviewFIB").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("fibId1").value;
    	  
          var output1 = document.getElementById('MathPreviewFIB');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForFIBI1 = function (button) {
    	  $("#MathPreviewFIB").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("icfibId1").value;
    	  
          var output1 = document.getElementById('MathPreviewFIB');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      
      window.typesetInputForIFIB = function (button,i) {
    	  $("#MathPrevieIFIB1").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("icfibId"+i).value;
    	  
          var output1 = document.getElementById('MathPrevieIFIB1');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForCMofifyOptionalFIB = function (button,i) {
    	  $("#MathPreviewfib").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("fibId"+i).value;
    	  
          var output1 = document.getElementById('MathPreviewfib');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForWMofifyOptionalFIB = function (button,i) {
    	 $("#MathPreviewfib").html('');
    	 $(".modal-title").html(' Answer Latex Format');
    	  var inputi = document.getElementById("micaId"+i).value;
    	  
          var outputi = document.getElementById('MathPreviewfib');
         
          
      //  button.disabled = true;
          outputi.innerHTML = inputi.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([outputi]).catch(function (err) {
          outputi.innerHTML = '';
          outputi.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });
    		  
      }
      window.typesetInputForMTP1 = function (button) {
    	  $("#MathPreviewMTP").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("mtpId1").value;
    	  
          var output1 = document.getElementById('MathPreviewMTP');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForMTPI1 = function (button) {
    	  $("#MathPreviewMTP").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("icmtpId1").value;
    	  
          var output1 = document.getElementById('MathPreviewMTP');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      window.typesetInputForCMofifyOptionalMTPI = function (button,i) {
      	 $("#MathPreviewMTP").html('');
      	$(".modal-title").html(' Answer Latex Format');
      	  var inputi = document.getElementById("mtpId"+i).value;
      	  
            var outputi = document.getElementById('MathPreviewMTP');
           
            
        //  button.disabled = true;
            outputi.innerHTML = inputi.trim();
          MathJax.texReset();
          MathJax.typesetClear();
          MathJax.typesetPromise([outputi]).catch(function (err) {
            outputi.innerHTML = '';
            outputi.appendChild(document.createTextNode(err.message));
            console.error(err);
          }).then(function () {
         //   button.disabled = false;
          });
      		  
        }
      window.typesetInputForWMofifyOptionalMTPI = function (button,i) {
       	 $("#MathPreviewMTP").html('');
       	$(".modal-title").html(' Answer Latex Format');
       	  var inputi = document.getElementById("micaId"+i).value;
       	  
             var outputi = document.getElementById('MathPreviewMTP');
            
             
         //  button.disabled = true;
             outputi.innerHTML = inputi.trim();
           MathJax.texReset();
           MathJax.typesetClear();
           MathJax.typesetPromise([outputi]).catch(function (err) {
             outputi.innerHTML = '';
             outputi.appendChild(document.createTextNode(err.message));
             console.error(err);
           }).then(function () {
          //   button.disabled = false;
           });
       		  
         }
      window.typesetInputForMTPI = function (button,i) {
     	 $("#MathPrevieIMTPI").html('');
     	$(".modal-title").html(' Answer Latex Format');
     	  var inputi = document.getElementById("icmtpId"+i).value;
     	  
           var outputi = document.getElementById('MathPrevieIMTPI');
          
           
       //  button.disabled = true;
           outputi.innerHTML = inputi.trim();
         MathJax.texReset();
         MathJax.typesetClear();
         MathJax.typesetPromise([outputi]).catch(function (err) {
           outputi.innerHTML = '';
           outputi.appendChild(document.createTextNode(err.message));
           console.error(err);
         }).then(function () {
        //   button.disabled = false;
         });
     		  
       }
      window.typesetInputForCMofifyOptionalMTP = function (button,i) {
    	  $("#MathPreviewMTP").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("mtpId"+i).value;
    	  
          var output1 = document.getElementById('MathPreviewMTP');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForWMofifyOptionalMTIP = function (button,i) {
    	 $("#MathPreviewMTP").html('');
    	 $(".modal-title").html(' Answer Latex Format');
    	  var inputi = document.getElementById("micaId"+i).value;
    	  
          var outputi = document.getElementById('MathPreviewMTP');
         
          
      //  button.disabled = true;
          outputi.innerHTML = inputi.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([outputi]).catch(function (err) {
          outputi.innerHTML = '';
          outputi.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });
    		  
      }
      window.typesetInputForTF1 = function (button) {
    	  $("#MathPreviewTF").html('');
      $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("tfId1").value;
    	  
          var output1 = document.getElementById('MathPreviewTF');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1;
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      window.typesetInputForTFI1 = function (button) {
    	  $("#MathPreviewTF").html('');
    	  $(".modal-title").html(' Answer Latex Format');
    	  var input1 = document.getElementById("ictfId1").value;
    	  
          var output1 = document.getElementById('MathPreviewTF');
         
          
      //  button.disabled = true;
          output1.innerHTML = input1.trim();
        MathJax.texReset();
        MathJax.typesetClear();
        MathJax.typesetPromise([output1]).catch(function (err) {
          output1.innerHTML = '';
          output1.appendChild(document.createTextNode(err.message));
          console.error(err);
        }).then(function () {
       //   button.disabled = false;
        });

      }
      
      
      
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
    "#to-demo",
    "#to-a11y",
    "#to-samples",
    "#to-ams-stub",
    "#to-siam-stub",
    "#to-stackoverflow-stub",
    "#to-ieee-stub",
    "#to-elsevier-stub",
    "#to-sponsorship-program",
    "#to-gettingstarted",
    "#to-apis",
    "#to-browsers"
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
script.src = 'https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js';
document.head.appendChild(script);








