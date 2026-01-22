// Tiny enhancements only (comfort-first; avoid clutter)
(function(){
  // Auto-dismiss success/info flashes after a few seconds (keeps UI calm)
  const flashes = document.querySelectorAll('.flash-success, .flash-info');
  flashes.forEach(el => {
    setTimeout(() => {
      el.style.transition = 'opacity .35s ease, transform .35s ease';
      el.style.opacity = '0';
      el.style.transform = 'translateY(-6px)';
      setTimeout(() => el.remove(), 380);
    }, 3800);
  });
})();
