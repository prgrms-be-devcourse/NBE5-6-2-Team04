function showLevelUpModal(levelName, levelId) {
    const modal = document.getElementById("levelUpModal");
    const text = document.getElementById("levelUpText");
    text.textContent = `축하합니다! Lv.${levelId} ${levelName}에 도달했습니다!`;

    modal.style.display = "flex";

    launchConfetti();

    setTimeout(() => {
        modal.style.display = "none";
    }, 4000);
}
function launchConfetti() {
    const duration = 2 * 1000;
    const end = Date.now() + duration;

    (function frame() {
        confetti({
            particleCount: 5,
            angle: 60,
            spread: 55,
            origin: { x: 0 }
        });
        confetti({
            particleCount: 5,
            angle: 120,
            spread: 55,
            origin: { x: 1 }
        });

        if (Date.now() < end) {
            requestAnimationFrame(frame);
        }
    })();
}