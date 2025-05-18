document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);
    const rawParam = urlParams.get("achievementName");

    if (rawParam) {
        const decoded = decodeURIComponent(rawParam.replace(/\+/g, ' '));
        console.log("🎯 감지된 achievementName:", decoded);
        showAchievementModal(decoded);

        const baseUrl = window.location.origin + window.location.pathname;
        window.history.replaceState({}, document.title, baseUrl);
    }
});

function showAchievementModal(achievementName) {
    console.log(document.getElementById("achievementCongratsModal"));
    document.getElementById("congratsMessage").innerText = `"${achievementName}" 업적을 달성했어요!`;
    // 모달 보이기
    document.getElementById("achievementCongratsModal").style.display = "block";
    // 🎊 메인 중앙 빵빠레 1회 강하게 터뜨리기
    confetti({
        particleCount: 150,
        spread: 90,
        origin: { y: 0.6 }
    });

    // 🎊 좌우 측면에서 연속적으로 터지는 빵빠레
    const duration = 2500;
    const animationEnd = Date.now() + duration;

    const interval = setInterval(() => {
        const timeLeft = animationEnd - Date.now();
        if (timeLeft <= 0) {
            clearInterval(interval);
            return;
        }

        confetti({
            particleCount: 30,
            angle: 60,
            spread: 70,
            origin: { x: 0, y: Math.random() * 0.8 }
        });
        confetti({
            particleCount: 30,
            angle: 120,
            spread: 70,
            origin: { x: 1, y: Math.random() * 0.8 }
        });
    }, 300);
}

function closeCongratsModal() {
    document.getElementById("achievementCongratsModal").style.display = "none";
}
