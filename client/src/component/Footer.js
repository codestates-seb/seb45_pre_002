import React from "react";
import "./Footer.css"; // 스타일링을 위한 CSS 파일

function Footer() {
	return (
		<footer className="footer">
			<div className="footer-container">
				<div className="footer-col">
					<img
						className="footer-logo--image"
						src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Stack_Overflow_icon.svg/1024px-Stack_Overflow_icon.svg.png?20190716190036"
						alt=""
					/>
				</div>
				<div className="footer-col">
					<h5 className="footer-col--title">STACK OVERFLOW</h5>
					<ul className="footer-col-list">
						<li>Questions</li>
						<li>Help</li>
					</ul>
				</div>
				<div className="footer-col">
					<h5 className="footer-col--title">PRODUCTS</h5>
					<ul className="footer-col-list">
						<li>Teams</li>
						<li>Advertising</li>
						<li>Collectives</li>
						<li>Talent</li>
					</ul>
				</div>
				<div className="footer-col">
					<h5 className="footer-col--title">COMPANY</h5>
					<ul className="footer-col-list">
						<li>About</li>
						<li>Press</li>
						<li>Work Here</li>
						<li>Legal</li>
						<li>Privacy Policy</li>
						<li>Teams of Service</li>
						<li>Contact Us</li>
						<li>Cookie Settings</li>
						<li>Cookie Policy</li>
					</ul>
				</div>
				<div className="footer-col">
					<h5 className="footer-col--title">STACK EXCHANGE NETWORK</h5>
					<ul className="footer-col-list">
						<li>Technology</li>
						<li>Culture & recreation</li>
						<li>Life & arts</li>
						<li>Science</li>
						<li>Professional</li>
						<li>Business</li>
						<li></li>
						<li>API</li>
						<li>Data</li>
					</ul>
				</div>
				<div className="footer-col">
					<div className="footer-social">
						<span>Blog</span>
						<span>Facebook</span>
						<span>Twitter</span>
						<span>LinkedIn</span>
						<span>instagram</span>
					</div>
					<div className="footer-copyright">
						<p>
							Site design / logo © 2023 Stack Exchange Inc;
							<br />
							user contributions licensed under CC BY-SA.
							<br /> rev 2023.8.10.43574
						</p>
					</div>
				</div>
			</div>
		</footer>
	);
}

export default Footer;
