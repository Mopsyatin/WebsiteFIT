BETTY BOUQUETS — SPRING BOOT READY FRONT-END (HOMEPAGE ONLY)

✅ What this pack contains
- Spring Boot-ready folder layout (templates + static)
- A working home page at route "/"
- CSS + JS + images included

📌 How your backend teammate uses this
1) Copy the folder structure into his Spring Boot project:
   src/main/resources/templates/home.html
   src/main/resources/static/css/styles.css
   src/main/resources/static/js/site.js
   src/main/resources/static/img/*.svg

2) Add this controller:

   @Controller
   public class PageController {
     @GetMapping("/")
     public String home() {
       return "home"; // loads templates/home.html
     }
   }

3) Run Spring Boot and open:
   http://localhost:8080/

✅ IMPORTANT RULES (so it doesn't break)
- In templates/home.html asset paths MUST be root-based:
  /css/styles.css
  /js/site.js
  /img/...

- Page links currently point to routes:
  /shop /about /contact /cart /login
  These can be connected later by adding controllers + templates.
   src/main/resources/static/js/site.js
   src/main/resources/static/img/*.svg

2) Add this controller:

   @Controller
   public class PageController {
     @GetMapping("/")
     public String home(){
       return "home";
     }

     // Optional: temporary placeholders so buttons don't 404
     @GetMapping("/shop") public String shop(){ return "home"; }
     @GetMapping("/about") public String about(){ return "home"; }
     @GetMapping("/contact") public String contact(){ return "home"; }
     @GetMapping("/cart") public String cart(){ return "home"; }
     @GetMapping("/login") public String login(){ return "home"; }
   }

✅ IMPORTANT RULES USED IN home.html
- All assets reference absolute paths:
  /css/styles.css
  /js/site.js
  /img/...

- All navigation links use routes (not .html):
  /shop, /about, /contact, /cart, /login

This makes it plug-and-play with Spring Boot.
   }

3) Run Spring Boot and open:
   http://localhost:8080/

✅ IMPORTANT RULES USED IN THIS FILE
- All assets are referenced using root paths:
  /css/styles.css
  /js/site.js
  /img/...

- All navigation links are routes (not .html):
  /shop, /about, /contact, /cart, /login

So the backend dev can later connect these routes one-by-one.
