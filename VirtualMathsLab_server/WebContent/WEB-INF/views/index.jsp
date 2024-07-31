
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">


<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VIRTUAL MATH LAB</title>
<link rel="icon" href="resource/images/VirtualMathsLabLogo.png" />


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resource/css/LoadingPage.css">
<link rel="stylesheet" href="resource/css/top.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
</style>

</head>

<body>
	<!--  new page -->
	<a id="button"></a>
	<div class="container-fluid">

		<div id="header" class="row"  >
			<div class="col-sm-12 col-md-12 col-xl-12 "
				style="background: rgb(57 116 130/ 70%);">
				<nav class="col-sm-12 col-md-12 col-xl-12 navStyle "> <span
					class="float-left"> <span class="menuSidebar" id="menu"
					style="">&#9776;</span>
					<div id="myNav" class="overlay">
<!-- 						<a href="javascript:void(0)" class="closebtn" onclick="closeNav()" -->
<!-- 							style="background-color: #7099a3; box-shadow: none;"> &times;</a> -->
						<div class="overlay-content">
							<center>
								<img
									src="resource/images/homepage/VirtualMathsLabLogoWithName.png"
									alt="logo" class="logoStyle" style="margin-top:-100px">
							</center>
							<!--    <button class="dropbtn" >TAKE AWAY <i class="fa fa-caret-down" aria-hidden="true"></i></button> -->
							<center>
								<span id="name" style="font-size: 20px; margin-bottom: 10px;">Virtual
									math Lab</span>
							</center>
							<hr>

							<button class="btn " id="takeAway">
								Take Away / फायदे <i class="fa fa-caret-down" aria-hidden="true"
									style="margin-left: 10px;"></i>
							</button>
							<a href="#Student" class="alink" id="student"
								style="margin-bottom: -3px;" hidden>Student / विद्यार्थी </a> <a
								href="#Teacher" class="alink" id="teacher"
								style="margin-bottom: -3px;" hidden>Teacher / शिक्षक </a> <a
								href="#Parent" class="alink" id="parent"
								style="margin-bottom: -3px;" hidden> Parent / पालक </a> <a
								href="#School" class="alink" id="school" hidden>School /
								शाळा </a>
							<button class="btn " id="feature">
								Features / वैशिष्ट्ये <i class="fa fa-caret-down"
									aria-hidden="true" style="margin-left: 23px;"></i>
							</button>
							<a href="#BasicFeature1" class="alink" id="BasicFeature"
								style="margin-bottom: -3px;" hidden> Basic Feature /<br> मूलभूत
								वैशिष्ट्ये </a> <a href="#TechnicalFeature1" class="alink"
								id="TechnicalFeature" style="margin-bottom: -3px;" hidden>
								Mathematical Feature/  <br> गणिती वैशिष्ट्ये </a> <a href="#Question"
								class="alink" id="typeQuestion" style="margin-bottom: -3px;"
								hidden>Types of Questions / <br> प्रश्नांचे प्रकार </a> <a
								href="#Question" class="alink" id="resultAnalysis" hidden>Result
								and Analysis  /<br> परिणाम विश्लेषण </a>
							<center>
								<a href="#topics" class="alink">Topics / विभाग </a>
							</center>
							<center>
								<a href="#Objectives" class="alink">Objective / उद्दिष्टे </a>
							</center>
							<center>
								<a href="#Background" class="alink"> Background /
									पार्श्वभूमी </a>
							</center>
							<center>
								<a href="#behind" class="alink">Thoughts behind / या मागील
									विचारसूत्र </a>
							</center>


						</div>
					</div>

				</span> <span class="float-right">
					<div class="dropdown">
						<button class="dropbtn">
							<a href="welcome" style="color: #fff;"> HOME</a>
						</button>
						<button class="dropbtn">
							<a href="login" style="color: #fff;"> LOGIN</a>
						</button>
						<button class="dropbtn">
							<a href="registration" style="color: #fff;"> REGISTER</a>
						</button>
					</div>

					<div style="clear: both"></div></nav>
				<p>
					<img src="resource/images/homepage/VirtualMathsLabLogoWithName.png"><br />
					<span id="name">Virtual math Lab</span><br /> <span id="name"
						style="font-size: 20px;">आभासी गणिती प्रयोग शाळा </span><br /> <br>
					<b>&ldquo;</b><span id="slogan">The internet technology
						gives us various forms of distance learning. It saves your money
						on tuition and extra classes. You can easily study at your home.
						It is also a less time-consuming way to study.</span> <b>&rdquo;</b> <br>
					<b>&ldquo;</b><span id="slogan" style="font-size: 14px;">
						इंटरनेट तंत्रज्ञान आपल्याला दूरस्थ शिक्षणाचे विविध प्रकार देते. हे
						ट्यूशन आणि अतिरिक्त वर्गांवर तुमचे पैसे वाचवते. तुम्ही तुमच्या घरी
						सहज अभ्यास करू शकता. अभ्यास करण्याचा हा एक कमी वेळ घेणारा मार्ग
						देखील आहे.</span> <b>&rdquo;</b>
				</p>
				<br> <br> 
			</div>

		</div>
		<!--     <a id="button"></a> -->
		<div class="row VMLBg" id="VMLBg">

			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>Philosophy</h1>

				<b><p>The very idea behind designing this website, is
						conceived due to the fear about math, the students have in their
						mind. Concept of numbers and math is introduced at a very early
						stage of schooling. At this juncture, students are not really
						aware about, what exactly Math is dealing with and where it can
						be used, like all other subjects. And obviously they don’t have
						any fear about math, at the entry point of schooling.</p>

					<p>But, as gradually they progress, the fear also starts
						developing, as the connection between math and its day to utility
						and application. Once the student starts losing track of the very
						need of math and its daily use, fear starts developing and it
						goes on intensifying with newer and newer concepts being
						introduced. This becomes a vicious circle of fear and distancing
						from math.</p>

					<p>
						If this process can be arrested, before it takes off, the chances
						of students getting friendly with math will enhance
						substantially. Two main reasons as to why the fear is getting
						developed, are, <br>i) the connect between math and its day
						to day application is not established strongly enough. <br>ii)
						and unless the earlier concept is understood, the student should
						not proceed further for the next topic.
					</p>

					<p>While developing this website, these very points are kept at
						the centre. If every new idea and concept is related and explained
						by taking into account the day to day needs for better and clear
						understanding, along with the possibilities where they can be
						applied.</p>

					<p>This covers the entire school math syllabus, largely as per
						Maharashtra State Secondary School syllabus. But almost all boards
						follow the same syllabus grossly, except some changes in sequence
						in which the topics are covered. Therefore this website is
						suitable for other boards like CBSE, ICSE, IB, including other
						state boards also.</p> </b>
			</div>
			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>पार्श्वभूमी</h1>
				<b style="font-size: 18px; letter-spacing: 1px; word-spacing: 1px;"><p>
						ही वेबसाईट तयार करण्यामागील महत्वाचे उद्दिष्ट म्हणजे, शालेय जीवनात
						गणिताबद्दल जी एक कायमची अढी तयार होते ती कशी दूर होणे शक्य होईल,
						या दिशेने प्रयत्न. मूल जेव्हा शाळेत जाऊ लागते, तेव्हा ते
						गणिताबाबतीत तितकेच अनभिज्ञ असते, जितके की इतिहास अथवा भूगोलाबद्दल.
						जसे भूगोल कुठे वापरावा लागतो तसेच गणिताचा वापर देखील कुठे, केव्हा
						आणि कसा करावा लागणार आहे हे देखील माहिती नसते. म्हणजेच जशी
						भूगोलाची भीती नसते, तशीच गणित विषयाची सुद्धा भीती असण्याची शक्यताच
						नसते.</p>

					<p>पण जस-जसे मुलाचे लौकिकार्थाने शिक्षण सुरु होते, तस-तसे
						मुलाच्या मनात या गणिताची भीती तयार होऊ लागते. तिला खतपाणी घातले
						जाते, कारण घरात तसेच शाळेत सगळीकडे तीच चर्चा असते की गणित म्हणजे
						खूपच कठीण विषय आणि काही कळायच्या आत ती फोफावतच वाढते. हळूहळू मूल
						गणित या विषयापासून दूरदूर जाऊ लागते. नंतर हा उलटा प्रवास रोखणे
						अतिशय अवघड असते आणि मग मूल बाहेर पडता न येणाऱ्या दुष्टचक्रात
						अडकतो. मुळात मुलाला हे लक्षात आणू देणं गरजेचं आहे, की आपण रोजच्या
						व्यवहारात हरघडी कळत-नकळत गणित वापरतच असतो. ते जेव्हा मुलाला कळेल,
						की आपला रोजचा व्यवहार हे गणितच आहे, तेव्हा त्याला गणिता बद्दल
						वाटणारी अढी दूर व्हायला नक्की मदतच होईल. आणि हेच या माध्यमाद्वारे
						साध्य करण्याचे प्रयोजन आहे.</p>
					<p>मूल या दुष्टचक्रात अडकूच नये आणि गणिताशी मैत्र निर्माण होईल
						हेच या प्रणालीचे मुख्य उद्दिष्ट आहे. गणिताची भीती निर्माण होण्याची
						दोन प्रमुख कारणे म्हणजे -
					<ol type="i">
						<li>रोजचा व्यवहार आणि त्यासाठी नकळत वापरले जाणारे गणित याचा
							संबंध अधोरेखित करणे. आणि</li>
						<li>आधीच्या संकल्पना दृढ झाल्याशिवाय किंवा समजल्याशिवाय पुढे
							न ढकलणे.</li>
					</ol>
					</p>
					<p>ही प्रणाली तयार करत असताना याच दोन गोष्टी डोळ्यासमोर होत्या.
						जर प्रत्येक नवी क्रिया आणि संकल्पना, रोजच्या व्यावहारिक गरजा
						याच्याशी जोडत पुढे गेलो तर मुलांची समज खूपच पक्की होईल आणि ते या
						संकल्पनांचा वापर सहजी करू शकतील.</p>
					<p>यात जरी मुख्यत्वेकरून महाराष्ट्र राज्य प्राथमिक आणि माध्यमिक
						शालेय अभ्यासक्रमातील संपूर्ण गणित अंतर्भूत केले असले, तरी साधारण
						सर्वच मंडळांचा अभ्यासक्रम थोड्या फार फरकाने सारखाच असतो. यात काही
						विभागांचा क्रम पुढे मागे झालेला असतो. त्यामुळे सर्वच म्हणजे, CBSE,
						ICSE, IB हे अभ्यासक्रम शिकणाऱ्या विद्यार्थ्यांना सुद्धा ही प्रणाली
						नक्कीच उपयोगी होईल.</p> </b>
			</div>
		</div>
		<div class="row VMLBg" id="VMLBg">

			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>About Virtual Math Lab</h1>
				<!--     <h1> About Virtual Maths Lab </h1> -->

				<b>Mathematics is the base for any scientific as well as day
					to-day activities. In many instances it has found that the student
					keep themselves away from mathematics just because of fear. The
					reasons are many but the impact of this is seen on the research
					mindset. It is evident and became a requirement to provide a
					platform which will challenge student's inquisitiveness and
					learning independently through assignments This platform gives a
					first of its kind experience as it is backed up by a team of
					enthusiastic and motivated team of experts. It is truly a noble
					initiative which is designed using open source software and made
					available to all the students across the country free of cost. </b>
			</div>
			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>आभासी गणिती प्रयोग शाळा</h1>
				<b style="font-size: 18px; letter-spacing: 1px; word-spacing: 1px;">गणित
					हा सर्व शास्त्रांचा तसेच दैनंदिन जीवनाचा एक अविभाज्य भाग आहे. असं
					नेहेमीच लक्षात आलंय की विद्यार्थी स्वतःला केवळ भीतीपोटी गणिता पासून
					दूर ठेवतात. याची अनेक कारण असतात. याचा विद्यार्थ्यांच्या जिज्ञासू
					वृत्तीवर खूप मोठ्या प्रमाणात परिणाम होणं हे अगदीच नैसर्गिक आहे. पण
					त्याचमुळे हे सुद्धा गरजेचं आहे की मुलांच्या या वृत्तीला खत पाणी
					घालणारे आणि त्यांना त्यांची जिज्ञासा शमवून आणि निरनिराळे प्रयोग
					करून स्वतंत्रपणे नवनवीन माहिती मिळविण्यासाठी आणि तपासण्यासाठी
					सुद्धा योग्य व्यासपीठ त्यांच्यासाठी उपलब्ध करून देणे! उत्साही आणि
					प्रेरणादायी तज्ञांच्या भक्कम पाठिंब्यावर आधारित असे व्यासपीठ
					विद्यार्थ्यांना एक मूलभूत अनुभव मिळवून देतो. हा अतिशय स्तुत्य आणि
					आवश्यक उपक्रम असून, त्यासाठी मुक्त स्रोत माहिती आणि ज्ञानाचा वापर
					करून, ही प्रणाली बनवलेली आहे. ही प्रणाली सगळ्यांनाच विनामूल्य
					उपलब्ध केली गेली आहे. </b>
			</div>
		</div>




		<!-- Feature-->
		<div class="row VMLBg" id="BasicFeature1">
			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>Basic Features</h1>
				<ul>
					<li>It is to be used like a video game rather than math
						tutorial, or self assessment tool. It will have various levels,
						which will be completed like games, mazes etc.</li>
					<li>All puzzles will be<b style="color: blue;"> MCQ </b>type
					</li>
					<li>This is suitable for anyone from age 3 onwards, depending
						upon one’s grasping.</li>
					<li>It will have various levels to be completed in different
						games.</li>
					<li>It will have 12 types of games, based on various sections
						of math.( Like numbers, operations, mensuration, algebra, bar
						graphs, statistics, geometry etc.) We call it a vertical (
						section) school math.</li>
					<li>This will be a<b style="color: blue;"> MULTILINGUAL </b>system.
						Currently <b style="color: blue;">ENGLISH + MARATHI.</b></li>
					<li>This system will be <b style="color: blue;">INDEPENDENT
					</b> of the <b style="color: blue;">STANDARD</b> in which the student
						is learning. (Student may or may not be school going)
					</li>
					<li><b style="color: blue;">Level of competency</b> will be
						assigned as one progresses.</li>
					<li>Points and stars will be awarded as appreciation and can
						be encashed for hints and getting new levels. (Proposed)</li>
					<li>Quick fire rounds</li>
					<li>This entire system will be available<b
						style="background-color: yellow; color: blue; padding: 0px;">
							FREE OF COST</b> to every user.
					</li>
					<li>The system is being designed as a tool for unsupervised
						learning, without <b style="color: blue;">external</b>
						intervention.
					</li>
					<li>System driven navigation of the student in case the
						student gets stuck up.</li>
				</ul>


			</div>
			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>मूलभूत वैशिष्ट्ये</h1>
				<ul>
					<!--   <li>या प्रणालीचा आराखडा अशा रीतीने तयार करण्यात आला आहे की, मुलांना संकल्पना आधारित गणित शिकणे सोपे जावे आणि हे करताना वेगवेगळी कोडी सोडवत पायरी  -->
					<!--   पायरीने आणि गणित समजावून घेत प्रगती करून घेता यावी. या पद्धतीचा शालेय तसेच शालाबाह्य, सर्वच विद्यार्थ्यांना उपयोग होणार आहे. एवढेच नव्हे तर असे प्रौढ देखील की, -->
					<!--    ज्यांना पुन्हा नव्याने गणित समजवून घेण्याची इच्छा आहे ते पण याचा लाभ घेऊ शकतील. विद्यार्थ्यांबरोबरच शिक्षक, पालक तसेच शाळा सुद्धा याचा फायदा करून घेऊ शकतील अशी वैशिष्ट्ये यात आहेत. <><<>> -->
					<!-- </li> -->
					<li>याचा वापर गणिताच्या परीक्षा असा न करता विडिओ गेम पद्धतीने
						करता येतो आणि त्याच बरोबर आपल्याला काय काय आणि किती समजले आहे याचा
						आढावा सुद्धा घेता येतो.</li>
					<li>यातील सर्व कोडीरूपी प्रश्न हे एकपर्यायी प्रकारचे आहेत.</li>
					<li>३ वर्षांपासून पुढील सर्वाना हे वापरता येईल.</li>
					<li>एक एक पातळी जिंकत (ओलांडत) पुढे पुढे जात राहायचे आहे.</li>
					<li>संपूर्ण गणित गुणोत्तर, संख्या ज्ञान, पूर्णांक - अपूर्णांक,
						भूमिती, व्यावहारिक गणित, त्रिकोणमिती इत्यादी १२ प्रकारात
						विभागण्यात आले आहे.</li>
					<li>शालेय अभ्यासक्रमात आपण एकाच विभाग तुकड्या तुकड्यात बरीच
						वर्षे शिकत असतो. परंतु येथे मात्र आपण त्या एका विभागाचा सलग अभ्यास
						करू शकतो.</li>
					<li>हे करत असताना मूल शिकत असलेल्या इयत्तेचाच अभ्यास केला
						पाहिजे असे नसून, सुरुवाती पासून किंवा सलग विभाग संपेपर्यंत पुढे
						जात राहता येते. (म्हणजे पाचवीतील मुलगा पूर्वी शिकलेला भाग पुन्हा
						शिकू शकतो किंवा तसाच पुढे जात आठवीतील गणित देखील करू शकतो.)</li>
					<li>ही प्रणाली मराठी आणि इंग्रजी अशा एकाच वेळी दोन्ही भाषेत
						उपलब्ध करून देण्यात आली आहे.</li>
					<li>ही प्रणाली सगळ्यांच्या वापरासाठी मोफत उपलब्ध असणार आहे.</li>
					<li>थोडक्यात म्हणजे ही प्रणाली सराव करणे, स्वतःची प्रगती
						तपासणे, शालेय गणिताच्या संकल्पना घोटून तयार करणे, स्पर्धा
						परीक्षांची तयारी अशा सर्वच बाबींसाठी बहुमूल्य आहे.</li>
				</ul>
			</div>
		</div>

		<div class="row VMLBg" id="topics">

			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>Topics</h1>
				<ol>
					<li>Numbers, Types of numbers
					<li>Basic Operations - Addition, Subtraction, Multiplication, Division
					<li>Algebra
					<li>Geometry
					<li>Coordinate Geometry
					<li>Trigonometry
					<li>Commercial Mathematics 
					<li>Measurement and Mensuration
					<li>Handling Data, Statistics
					<li>Probability 
					<li>Patterns, Sequences, Arithmetic Progression
					<li>Sets			
				</ol>
			</div>
			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>विभाग</h1>
				<ol>
					<li>संख्याज्ञान, संख्याचे प्रकार 
					<li>मूलभूत क्रिया - बेरीज, वजाबाकी, गुणाकार, भागाकार 
					<li>बीजगणित 
					<li>भूमिती 
					<li>निर्देशक भूमिती 
					<li>त्रिकोणमिति 
					<li>व्यावहारिक गणित 
					<li>मापन आणि महत्वमापन 
					<li>माहितीचे व्यवस्थापन, सांख्यिकी 
					<li>संभाव्यता
					<li>आकृतीबंध, श्रेढी, अंकगणिती श्रेढी 
					<li>संच				
				</ol>
				
				
				
				
			</div>
		</div>


		<div class="row VMLBg" id="TechnicalFeature1">
			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6 ">
				<h1>Mathematical Features</h1>
				<ul>
					<li>For every type of sum, numerous questions are designed.</li>
					<li>4 types of tests are designed - Practice, Mock, Actual and
						Competitive tests.</li>
					<li>Unlimited practice sessions</li>
					<li>Standard as well as configurable tests, based on topics, <b>Degree
							of difficulty</b></li>
					<li>Time allotted for each question is Degree of Difficulty
						based.</li>
					<li>Configurable test for the entire class or school</li>
					<li>Distinct questions set for each.</li>
					<li>Instantaneous results with analysis and comments.</li>
					<li>Areas of weakness identification</li>
					<li>Referral reading material</li>
					<li>One can explore the capacity of oneself</li>
					<li>Solutions provided of each question for better
						understanding</li>
					<li>Can be used on desktops, laptops or mobiles</li>
					<li>Super large question set of more than <b
						style="color: blue;">1 million </b>questions
					</li>
					<li>Can leave Virtual Math Lab anywhere and start from the
						point where it was left</li>
					<li>Competitive test are specially designed for high
						performing students</li>
				</ul>
			</div>

			<div id="LeftSec" class="col-sm-6 col-md-6 col-xl-6">
				<h1>गणिती वैशिष्ट्ये</h1>
				<ul>

					<li>प्रत्येक प्रकारच्या बेरजेसाठी, असंख्य प्रश्नांची रचना केली
						आहे.</li>
					<li>4 प्रकारच्या चाचण्या तयार केल्या आहेत - सराव, मॉक,
						वास्तविक आणि स्पर्धात्मक चाचण्या.</li>
					<li>अमर्यादित सराव सत्रे</li>
					<li>मानक तसेच जुळवणी करण्यायोग्य चाचण्या, विषयांवर आधारित,
						अडचणीची पातळी</li>
					<li>प्रत्येक प्रश्नासाठी दिलेला वेळ अडचणीच्या पातळी आधारित
						आहे.</li>
					<li>संपूर्ण वर्ग किंवा शाळेसाठी जुळवणी करण्यायोग्य चाचणी</li>
					<li>प्रत्येकासाठी वेगळे प्रश्न संच केले आहेत.</li>
					<li>विश्लेषण आणि टिप्पण्यांसह त्वरित परिणाम.</li>
					<li>कमकुवतपणा ओळखण्याचे क्षेत्र</li>
					<li>संदर्भ वाचन साहित्य</li>
					<li>एखादी व्यक्ती स्वतःची क्षमता शोधू शकते</li>
					<li>चांगल्या प्रकारे समजून घेण्यासाठी प्रत्येक प्रश्नाचे
						समाधान प्रदान केले आहे</li>
					<li>डेस्कटॉप, लॅपटॉप किंवा मोबाईलवर वापरता येईल</li>
					<li>1 लाखाहुन अधिक प्रश्नांचा सुपर लार्ज प्रश्न संच</li>
					<li>आभासी गणिती प्रयोग शाळा कुठेही सोडू शकता आणि जिथे सोडले
						होते तिथून सुरू करू शकता</li>
					<li>स्पर्धात्मक चाचणी विशेषतः उच्च कामगिरी करणाऱ्या
						विद्यार्थ्यांसाठी डिझाइन केलेली आहे</li>

				</ul>
			</div>
		</div>

		<!--    For Feature -->
		<!-- Points-->
		<div class="row VMLBg" id="Question">
			<div id="LeftSec VMLBg" class="col-sm-6 col-md-6 col-xl-6">
				<div class="row VMLBg" style="border-color: #f5e7e7;">
					<div id="LeftSec VMLBg" class="col-sm-6 col-md-6 col-xl-6">
						<h1 style="margin-top: 5px;">Types of Question</h1>
						<ul>
							<li>All questions are MCQ type</li>
							<li>Single correct or multiple correct</li>
							<li>Group question based on Image / paragraph</li>
							<li>Fill in the blanks</li>
							<li>Match the pairs</li>
							<li>Group questions</li>
							<li>True or False</li>
							<li>Audio and Video</li>
							<!--   <li>True or False -->
							<!--   </li> -->
							<!--   <li>Audio and Video</li>		 -->
						</ul>
					</div>
					<div id="LeftSec VMLBg" class="col-sm-6 col-md-6 col-xl-6">
						<h1 style="margin-top: 5px;">प्रश्नाचे प्रकार</h1>
						<ul>
							<li>सर्व प्रश्न MCQ पद्धतीनेच विचारलेले आहेत</li>
							<li>एक पर्यायी / अनेक पर्यायी</li>
							<li>परिचछेद अथवा आकृती आधारित समूह प्रश्न</</li>
							<li>गाळलेल्या जागा भरा</</li>
							<li>जोड्या लावा</li>
							<li>समूह प्रश्न</li>
							<li>बरोबर व चूक</li>
							<li>श्राव्य (प्रश्न ऐकून उत्तर देणे) आणि दृकश्राव्य
								(चित्रफीत बघून उत्तर देणे)</</li>

						</ul>
						<br> <br> <br>
					</div>
				</div>
			</div>
			<div id="LeftSec VMLBg" class="col-sm-6 col-md-6 col-xl-6">
				<div class="row VMLBg" style="border-color: #f5e7e7;">
					<div id="LeftSec VMLBg" class="col-sm-6 col-md-6 col-xl-6">
						<h1 style="margin-top: 5px;">Result and Analysis</h1>
						<ul>
							<li>Result is generated and shared on screen after every
								test.</li>
							<li>Topic Wise, section Wise, Vertical Wise result and
								progress is also generated</li>
							<li>Life time report for each individual is always available</li>
							<li>Result for the class can also be generated</li>
							<li>Analysis for individual, class or for the school can be
								provided</li>
							<li>Performance assessment of a teacher also can be provided</li>
						</ul>
					</div>
					<div id="LeftSec VMLBg" class="col-sm-6 col-md-6 col-xl-6">
						<h1 style="margin-top: 5px;">परिणाम आणि विश्लेषण</h1>
						<ul>
							<li>प्रत्येक चाचणीनंतर निकाल तयार केला जातो आणि स्क्रीनवर
								सामायिक केला जातो.</li>
							<li>विषयवार, विभागवार, अनुलंब निहाय निकाल आणि प्रगती देखील
								तयार केली जाते</li>
							<li>प्रत्येक व्यक्तीसाठी लाइफ टाइम रिपोर्ट नेहमी उपलब्ध असतो
							</li>
							<li>वर्गासाठी निकाल देखील तयार केला जाऊ शकतो</</li>
							<li>वैयक्तिक, वर्ग किंवा शाळेसाठी विश्लेषण प्रदान केले जाऊ
								शकते</li>
							<li>शिक्षकाच्या कामगिरीचे मूल्यांकन देखील प्रदान केले जाऊ
								शकते</li>

						</ul>
					</div>
				</div>
			</div>
		</div>


		<ul>

			<!--    </div> -->

			<!--    For Points -->

			<!--    For Student -->
			<div class="row VMLBg" id="Student">
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>TAKE AWAYS FOR STUDENTS</h1>
					<ul>

						<li>Division of each mathematical section into several parts
							according to its concepts</li>
						<li>Multiple types of calculations for each section</li>
						<li>Every question is available in both Marathi and English
							language.</li>
						<li>Answers available with correct steps and explanation for
							each question.</li>
						<li>All questions available with a certain time required to
							solve it mathematically.</li>
						<li>Difficulty level according to mathematics.</li>
						<li>Math’s practice set according to difficulty level</li>
						<li>Unlimited practice opportunities</li>
						<li>Math’s sets according to practice, mock, actual and
							competitive exams</li>
						<li>Performance report according to each set of exams</li>
						<li>Number of Questions Left, Correct Answers, Missed Answers
							in Report</li>
						<li>Trajectory to next levels based on performance in report</li>
						<li>Overall performance review</li>
						<li>Study material for Reference.</li>
					</ul>
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 ">

					<img src="resource/images/homepage/thumb.gif" width="100%">
					<!--    <footer style="background-color:rgb(187 206 211 / 70%);"> -->
					<!--   <button type="button" class="btn btn-info" >Read More </button> -->
					<!-- </footer> -->
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">

					<h1>विद्यार्थ्यांसाठी लाभ आणि फायदे</h1>

					<ul>
						<li>प्रत्येक गणिती विभागाची त्यातील संकल्पने नुसार अनेक भागात
							विभागणी</li>
						<li>प्रत्येक विभागासाठी अनेक प्रकारची गणिते</li>
						<li>एकाच प्रकारच्या गणितावर आधारित अनेक गणिते</li>
						<li>प्रत्येक गणित मराठी आणि इंग्रजी दोन्ही भाषेत</li>
						<li>प्रत्येक गणित सोडविण्याच्या योग्य पायऱ्यांसह आणि खुलाशासह
							उत्तर</li>
						<li>गणितानुसार सोडविण्यासाठी लागणाऱ्या ठराविक वेळेसह</li>
						<li>गणितानुसार काठिण्य पातळी</li>
						<li>काठिण्य पातळीनुसार गणितांचा सराव संच</li>
						<li>अमर्यादित सराव संधी</li>
						<li>सराव, मॉक, प्रत्यक्ष आणि स्पर्धा परीक्षा नुरुप गणित संच</li>
						<li>प्रत्येक संच परीक्षे नुसार कामगिरी अहवाल</li>
						<li>अहवालात सोडलेले प्रश्न, बरोबर आलेली उत्तरे, चुकलेली
							उत्तरे संख्या</li>
						<li>अहवालातील कामगिरी नुसार पुढील पातळ्यांसाठी मार्गक्रमण</li>
						<li>एकूण कामगिरीचा आढावा</li>
						<li>संदर्भ वाचनासाठी साहित्य</li>
						<li>संकल्पना समजावून देण्यासाठी दृक्श्राव्य चित्रफिती</li>
						<li>थोर गणिती आणि गणितासंबंधी अवांतर माहिती</li>
					</ul>
				</div>

			</div>
			<!--    For Student -->

			<!--    For Teacher -->
			<div class="row VMLBg" id="Teacher">
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>TAKE AWAYS FOR TEACHERS</h1>
					<ul>

						<li>Time savings to be given according to student's ability</li>
						<li>To get lot of practice from the students</li>
						<li>Preparation of question paper for student practice test</li>
						<li>Preparation of question papers for actual examination .</li>
						<li>Guaranteed different question papers for each student</li>
						<li>Eliminating time required for checking answer sheets</li>
						<li>A ready review of the performance of each student as well
							as the entire class</li>
						<li>Identifying students who are progressing or lagging
							behind in the subject</li>
						<li>Special practice for lagging students without wasting
							their own time</li>
						<li>You can contribute to the development of this system in
							many ways</li>
						<li>Can be a contributor, create ideal math’s questions with
							solutions.</li>
						<li>You can create materials that explain concepts to
							strengthen student understanding of the subject</li>
						<li>Ready-made collection of more than 5 lakh questions with
							respective answers</li>
						<li>Facility to prepare question paper as per requirement</li>
					</ul>
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 ">

					<img src="resource/images/homepage/teacher.gif" width="100%">
					<!--    "WebContent/resource/images/homepage/teacher.gif" -->
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>शिक्षकांसाठी सुविधा</h1>
					<ul>
						<li>विद्यार्थ्यांच्या कुवतीनुसार द्यावा लागणारा वेळेची बचत</li>
						<li>मुलांकडून भरपूर सराव करून घेण्यासाठी</li>
						<li>मुलांच्या सराव परीक्षेसाठी प्रश्न पत्रिका तयार करणे</li>

						<li>प्रत्येक विद्यार्थ्यांसाठी हमखास वेगवेगळी प्रश्न पत्रिका
						</li>
						<li>उत्तर पत्रिका तपासणीसाठी लागणारा वेळ काढून टाकणे</li>
						<li>प्रत्येक विद्यार्थ्यांच्या तसेच सर्व वर्गाच्या कामगिरीचा
							तयार आढावा</li>
						<li>विषयात गती असलेली मुले किंवा मागे पडणारी मुले ओळखणे</li>
						<li>मागे पडणाऱ्या विद्यार्थ्यांसाठी स्वतःचा वेळ खर्च न होऊ
							देता विद्यार्थ्यांना विशेष सराव</li>
						<li>या प्रणालीच्या विकाससाठी हातभार अनेक प्रकारे लावू शकता</li>
						<li>योगदानकर्ते होऊ शकता, गणिताची आदर्श उत्तरे तयार करू शकता
						</li>
						<li>मुलांची विषयाची समज दृढ होण्याच्या दृष्टीने संकल्पना
							समजावून देणारे साहित्य तयार करू शकता</li>
						<li>सुमारे ५ लाख पेक्षाही जास्त प्रश्नांची आदर्श उत्तरांसह
							तयार पेढी</li>
						<li>स्वतः गरजेनुसार प्रश्नपत्रिका तयार करण्याची सोय</li>
					</ul>

				</div>

			</div>
			<!--    For Teacher -->
			<!--    For Parent  -->
			<div class="row VMLBg" id="Parent">
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>TAKE AWAYS FOR PARENTS</h1>
					<ul>
						<li>Ready material available for studying and practicing at
							home</li>
						<li>Can understand the benefits / results from practice</li>
						<li>The school does not appear to be the source of
							information on the student’s development.</li>
						<li>Using this system to reinforce a section or concept</li>
						<li>Useful for competitive exams and special practice</li>

					</ul>
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 ">

					<img src="resource/images/homepage/mod.gif" width="100%"
						height="80%">

				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">

					<h1>पालकांसाठी लाभ</h1>
					<ul>


						<li>घरी अभ्यास आणि सराव करून घेण्यासाठी तयार साहित्य मिळते.</li>
						<li>सरावातून मिळणारे फायदे / परिणाम पाहू शकतात .</li>
						<li>पाल्याच्या प्रगतीची माहिती शाळेवर अवलंबून न राहता दिसते .</li>
						<li>एखादा विभाग अथवा संकल्पना दृढ करण्यासाठी या प्रणालीचा
							उपयोग करून घेणे .</li>
						<li>स्पर्धा परीक्षांसाठी आणि विशेष सरावासाठी उपयोगी .</li>

					</ul>

				</div>

			</div>
			<!--    For Parent -->
			<!--    For school  -->
			<div class="row VMLBg" id="School">
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>TAKE AWAYS FOR SCHOOLS</h1>
					<ul>
						<li>Use this system to monitor the preparation or progress of
							any class</li>
						<li>Ability to understand subject of any class</li>
						<li>A comparative study of students from two (multiple)
							cohorts of the same grade</li>
						<li>For statistical inspection and preparation of numerical
							report of results</li>
						<li>Teacher preparation for teaching</li>
						<li>Raw links to teachers' teaching</li>
						<li>Kind of help that teachers need in teaching.</li>

					</ul>
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 ">

					<img src="resource/images/homepage/school12.jpg" width="100%">
					<!--   "WebContent/resource/images/homepage/school12.jpg" -->
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>संस्थांसाठी लाभ</h1>
					<ul>
						<li>या प्रणालीचा उपयोग कोणत्याही वर्गाची तयारी किंवा प्रगती
							पाहण्यासाठी</li>
						<li>कोणत्याही वर्गाची विषय समजण्याची कुवत</li>
						<li>एकाच इयत्तेतील दोन (अनेक) तुकड्यातील विद्यार्थ्यांचा
							तौलनिक अभ्यास</li>
						<li>सांख्यिकी पाहणीसाठी आणि निकालाचा संख्यात्मक अहवाल तयार
							करणे</li>
						<li>शिक्षकांची शिकवण्याची तयारी</li>
						<li>शिक्षकांचे शिकवण्यातील कच्चे दुवे</li>
						<li>शिक्षकांना शिकवताना कोणत्या प्रकारची मदत हवी आहे</li>

					</ul>

				</div>

			</div>
			<!--    For School -->

			<div class="row VMLBg" id="Background">
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 ">
					<h1>Virtual Math Lab Background</h1>
					<ul>
						<li>The stubbornness in the minds of children about
							mathematics</li>
						<li>The reasons behind its formation</li>
						<li>When a student comes to school, he has no fear of
							mathematics</li>
						<li>But both of these things start to happen as the student
							moves upstairs</li>
						<li>Broken or non-existent association between math and daily
							practice</li>
						<li>Teaching children with different perceptions together is
							a daunting challenge for teachers (for anyone)</li>
					</ul>


				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 ">

					<img src="resource/images/homepage/ams.gif" width="100%">
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>पूर्वपीठिका</h1>
					<ul>
						<li>गणित बद्दल मुलांच्या मनात निर्माण होणारी अढी</li>
						<li>ती निर्माण होण्या मागील कारणे</li>
						<li>विद्यार्थी शाळेत येतो तेव्हा त्याच्या मनात गणिताबद्दल अढी
							सुद्धा नसते आणि भीती सुद्धा नसते</li>
						<li>परंतु विद्यार्थी जसजसा वरच्या वर्गात जातो तसतशा या दोन्ही
							गोष्टी घडू लागतात</li>
						<li>गणित आणि दैनंदिन व्यवहार याची तुटलेली किंवा रहात नसलेली
							सांगड</li>
						<li>भिन्न समज शक्ति असलेल्या मुलाण एकत्र शिकवणे हे
							शिक्षकांसाठी (कोणाही साठी )एक न पेलणारे आव्हान आहे</li>
					</ul>

				</div>
			</div>

			<div class="row VMLBg" id="behind">
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>Thoughts behind Virtual Math Lab</h1>
					<ul>
						<li>Mathematics can be a game</li>
						<li>Any game is played without a hitch</li>
						<li>Today's school math is taught intermittently (that is, a
							single subject is divided into 4 syllables and the continuity is
							lost)</li>
						<li>Is it possible to learn / teach mathematics in a
							continuous manner by removing this shortcoming?</li>
						<li>Is it possible to learn mathematics without teaching?</li>
						<li>Everyone's perceptive powers are different, so if
							everyone gets to learn math at a personal pace, there will be no
							pressure to learn, learning will be fun</li>
						<li>The child wants the freedom to learn the math he wants</li>
						<li>According to division, if mathematics breaks out of this
							conventional concept, there will be free travel</li>
					</ul>
				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4 AddFloatforDesk">

					<img src="resource/images/homepage/unnamed.gif" width="100%">

				</div>
				<div id="LeftSec" class="col-sm-4 col-md-4 col-xl-4">
					<h1>या मागील विचारसूत्र, / विचारधारा</h1>
					<ul>
						<li>गणित हा एक खेळ होऊ शकेल</li>
						<li>कोणताही खेळ खेळताना मागे न लागता खेळला जातो</li>
						<li>आजचे शालेय गणित ही तुटक पद्धतीने शिकवले जाते ( म्हणजे एकच
							विषय 4 यत्ता मध्ये विभागला जातो आणि त्यातील सलगपणा घालवला जातो</li>
						<li>हा तुटकपणा घालवून सलग पद्धतीने गणित शिकता / शिकवता येईल
							का</li>
						<li>न शिकवता गणित शिकता येईल का</li>
						<li>प्रत्येकाची समज शक्ति भिन्न असते त्यामुळे प्रत्येकाला
							वैयक्तिकरित्या झेपणाऱ्या वेगाने गणित शिकायला मिळाल्यास शिकण्याचा
							दबाव येणार नाही, शिकणे आनंददायक होईल</li>
						<li>मुलाला हवं ते गणित शिकण्याचे स्वातंत्र्य हवे</li>
						<li>यत्ते नुसार गणित या रूढ संकल्पनेतून बाहेर पडल्यास मुक्त
							प्रवास होईल</li>
					</ul>

				</div>

			</div>

			<div class="row VMLBg" id="Objectives">

				<div id="LeftSec" class="col-sm-12 col-md-6 col-xl-6 ">
					<h1>Objectives</h1>
					<ul>
						<li>Provide a unique platform for the students to practice
							mathematics</li>
						<li>Bilingual platform with "English" and a "local language"
							as a feature</li>
						<li>Create a learning environment which will enable thought
							process and application of mind</li>
						<li>Personalised learning and avenues for self-learning with
							focus on personalized learning style</li>
						<li>Focus on "concept clarity" than "marks in examination"</li>
						<li>Unique goal setting based on novel methods of learning</li>
						<li>Platform to nurture the talent without cost</li>
						<li>Timeless, limitless, and always available robust
							framework</li>
						<li>Developed to learn mathematics as fun and a well-designed
							game</li>

					</ul>

				</div>
				<div id="LeftSec" class="col-sm-12 col-md-6 col-xl-6">
					<h1>उद्दिष्टे</h1>
					<ul>
						<li>वरील सर्व विचार प्रत्यक्षात अमलात येतील अशी पद्धत तयार
							करणे</li>
						<li>गणिताचे App हा एक खेळ असेल ( निदान लहान वयातील मुलांसाठी
							)</li>
						<li>बालवाडी ते दहावी असे सर्व गणित यात समाविष्ट करणे</li>
						<li>पूर्णपणे मानवी सहभागा शिवाय गणित करणे</li>
						<li>मुलाने आज पर्यन्त केलेल्या गणिताचे विश्लेषण करून देणे</li>
						<li>योग्य वेळी आणि आवश्यक तेथे मार्गदर्शनाची सोय करणे</li>
						<li>पुरेसा (जो की यातील व्यवस्था पुरेसा याचा अर्थ ठरवेल) सराव
							करवून घेणे</li>
						<li>प्रत्येक पातळीवर प्रवेश पूर्व आवश्यक पूर्वज्ञान ठरवणे आणि
							ते तपासणे</li>
						<li>नसल्यास ते जिथून मिळणार आहे त्या ठिकाणी विद्यार्थ्याला
							नेणे</li>
						<li>App द्वारे सराव, चाचणी, प्रत्यक्ष आणि स्पर्धात्मक अशा 4
							पद्धतीने शिक्षण आणि तपासणी</li>
						<li>आवश्यक तिथे text, PPT, श्राव्य आणि दृकश्राव्य पद्धतीने
							सहाय्य आणि मार्गदर्शन</li>
						<li>हे App शिक्षकांसाठी सुद्धा उपयोगी होईल या पद्धतीने बांधणी
						</li>
						<li>वर्गासाठी एकत्र सराव अथवा चाचणी परीक्षा घेणे आणि त्यातून
							त्या विषयातील कच्चे दुवे शोधणे, वर्गातील मुलांचा आणि शिक्षकांचा
							सुद्धा अहवाल तयार होईल</li>
						<li>संपूर्ण शाळेसाठी सुद्धा याच सोई देणे</li>
						<li>ज्याला विशेष गती आहे अशा विद्यार्थ्याला स्वतंत्रपणे पुढे
							नेणे</li>
						<li>हे App इंग्रजी आणि मराठी यासह दोन्ही भाषेत उपलब्ध करून
							देणे</li>
						<li>प्रत्येक गणितासाठी सकारण उत्तर पाहण्याची सोय देणे</li>
						<li>विषयाशी संबंधित text, PPT, श्राव्य आणि दृकश्राव्य
							संग्रहालय</li>
						<li>जास्तीत जास्त शिक्षकांचा सहभाग मिळवणे</li>
						<li>हे App पालकांसाठी सुद्धा उपयुक्त करणे</li>
						<li>कमीत कमी 1,00,000 गणिते यात उपलब्ध करून देणे</li>
						<li>मानवी सहभागाशिवाय प्रश्न पत्रिका तयार करणे आणि तपासून
							विश्लेषण करणे</li>
						<li>गणित सोडवण्यासाठी लागणारा वेळ विद्यार्थ्यांच्या प्रतिसादा
							नुसार आपोआप बदलता राहील</li>
						<li>गणिताची काठिण्य पातळी विद्यार्थ्यांच्या प्रतिसादा नुसार
							आपोआप बदलता राहील</li>
						<li>हव्या त्या विभागा वर किंवा हव्या त्या विभागांवर एकत्रित
							पणे प्रश्न पत्रिका काढण्याची सोय</li>
					</ul>
				</div>
			</div>
	</div>
	<footer> Virtual Math Lab @ COEP </footer>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<script src="resource/js/bootstrap.min.js"></script>

	<script src="resource/js/material.min.js"></script>
	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>

	<script src="resource/js/jquery.viewbox.min.js"></script>
	<script src="resource/js/run_prettify.js"></script>

	<script type="text/javascript" src="resource/src/menu.js"></script>
	<script type="text/javascript" src="resource/js/slick.js"></script>

	<script>
		$(document).ready(function() {
			$('.customer-logos').slick({
				slidesToShow : 4,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 1500,
				arrows : false,
				dots : false,
				pauseOnHover : false,
				responsive : [ {
					breakpoint : 768,
					settings : {
						slidesToShow : 4
					}
				}, {
					breakpoint : 520,
					settings : {
						slidesToShow : 1
					}
				} ]
			});
		});

		// jquery viewbox script start

		$(document).on('click', '.navbar-collapse.in', function(e) {
			if ($(e.target).is('a')) {
				$('.navbar-collapse').css('height', '0');
				$(".navbar-collapse").removeClass("in");
			} else {
				$('.navbar-collapse').css('height', 'auto');
				$(".navbar-collapse").addClass("in");
			}

		});

		$(document).ready(function() {
			if ($(window).width() > 768) {
				$('#myNavbar').addClass("pull-right");
			} else {
				$('#myNavbar').removeClass("pull-right");
				$('.nav, .navbar-nav').css("margin-top", "50px");

			}
		});
	</script>
	<script>
		new WOW().init();
	</script>
	<script src="resource/src/top.js"></script>
	<!-- //jquery viewbox script end -->
	<script>
		$(function() {

			$('.thumbnail').viewbox();
			$('.thumbnail-2').viewbox();

			(function() {
				var vb = $('.popup-link').viewbox();
				$('.popup-open-button').click(function() {
					vb.trigger('viewbox.open');
				});
				$('.close-button').click(function() {
					vb.trigger('viewbox.close');
				});
			})();

		});
	</script>
	<script>
		// for dropdown
		$(
				"#BasicFeature,#TechnicalFeature,#typeQuestion,#resultAnalysis,#student,#teacher,#school,#parent").click(function() {
					$("h1").css('margin-top', '23px');
// 					 console.log("fgfgfg");
				});
		$("#takeAway").hover(function() {
			$("#student,#teacher,#school,#parent").prop("hidden", false);

		});

		$("#takeAway").click(function() {
			// 	$("#student,#teacher,#school,#parent").prop("hidden",true);
			$("#student,#teacher,#school,#parent").toggle();
		});
		$("#feature")
				.hover(
						function() {
							$(
									"#BasicFeature,#TechnicalFeature,#typeQuestion,#resultAnalysis")
									.prop("hidden", false);

						});
		$("#feature")
				.click(
						function() {
							// 	$("#student,#teacher,#school,#parent").prop("hidden",true);
							$(
									"#BasicFeature,#TechnicalFeature,#typeQuestion,#resultAnalysis")
									.toggle();
						});

		$("#menu").hover(function() {
// 			$(".VMLBg,#header").css("margin-left", "200px");
		
			$("#myNav").css("width", "15%");

		});

		$(".alink").click(function() {
							$("#myNav").css("width", "0%");
							$("#student,#teacher,#school,#parent").prop("hidden", true);
							$("#BasicFeature,#TechnicalFeature,#typeQuestion,#resultAnalysis").prop("hidden", true);
						});

		function closeNav() {
			$("#myNav").css("width", "0%");
// 			$(".VMLBg,#header").css("margin-left", "0px");
			$("#student,#teacher,#school,#parent").prop("hidden", true);
			$("#BasicFeature,#TechnicalFeature,#typeQuestion,#resultAnalysis")
					.prop("hidden", true);
		}
		$("#myNav").mouseleave(function() {
			closeNav();
							
// 							$("#student,#teacher,#school,#parent").prop(
// 									"hidden", true);
// 							$(
// 									"#BasicFeature,#TechnicalFeature,#typeQuestion,#resultAnalysis")
// 									.prop("hidden", true);
						});
	</script>
</body>
</html>