document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    if (params.get("achievement") === "true") {
        openAchievementModal();
        document.getElementById("confettiSound").play();
        confetti({
            particleCount: 150,
            spread: 80,
            origin: { y: 0.6 }
        });
    }
});

function openAchievementModal() {
    document.getElementById("achievementModal").style.display = "block";
}

// function closeModal() {
//     document.getElementById("achievementModal").style.display = "none";
// }
