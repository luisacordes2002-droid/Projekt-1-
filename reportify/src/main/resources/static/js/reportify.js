document.addEventListener("DOMContentLoaded", () => {
    const header = document.querySelector(".app-header");
    const menuButton = document.querySelector("[data-menu-toggle]");

    if (header && menuButton) {
        menuButton.addEventListener("click", () => {
            const isOpen = header.classList.toggle("is-open");
            menuButton.setAttribute("aria-expanded", String(isOpen));
        });
    }

    document.querySelectorAll("textarea[maxlength]").forEach((textarea) => {
        const counter = document.querySelector(`[data-counter-for="${textarea.id}"]`);
        if (!counter) return;

        const maximum = Number(textarea.maxLength);
        const updateCounter = () => {
            const length = textarea.value.length;
            counter.textContent = `${length} / ${maximum} Zeichen`;
            counter.classList.toggle("is-near-limit", length >= maximum * 0.9 && length < maximum);
            counter.classList.toggle("is-at-limit", length >= maximum);
        };

        textarea.addEventListener("input", updateCounter);
        updateCounter();
    });

    document.querySelectorAll("[data-password-toggle]").forEach((button) => {
        button.addEventListener("click", () => {
            const input = document.getElementById(button.dataset.passwordToggle);
            if (!input) return;
            const isVisible = input.type === "text";
            input.type = isVisible ? "password" : "text";
            button.textContent = isVisible ? "Anzeigen" : "Ausblenden";
            button.setAttribute("aria-pressed", String(!isVisible));
        });
    });

    const searchInput = document.getElementById("historySearch");
    const shiftSelect = document.getElementById("historyShift");
    const resetButton = document.getElementById("historyReset");
    const emptyMessage = document.getElementById("historyEmpty");
    const historyCards = Array.from(document.querySelectorAll('[data-history="true"]'));

    if (searchInput && shiftSelect && resetButton && emptyMessage) {
        const filterHistory = () => {
            const search = searchInput.value.trim().toLocaleLowerCase("de");
            const shift = shiftSelect.value;
            let visibleCount = 0;

            historyCards.forEach((card) => {
                const matchesSearch = (card.dataset.search || "").toLocaleLowerCase("de").includes(search);
                const matchesShift = !shift || card.dataset.shift === shift;
                const visible = matchesSearch && matchesShift;
                card.hidden = !visible;
                if (visible) visibleCount += 1;
            });

            emptyMessage.style.display = visibleCount === 0 ? "block" : "none";
        };

        searchInput.addEventListener("input", filterHistory);
        shiftSelect.addEventListener("change", filterHistory);
        resetButton.addEventListener("click", () => {
            searchInput.value = "";
            shiftSelect.value = "";
            filterHistory();
            searchInput.focus();
        });
    }

    const deleteDialog = document.getElementById("deleteDialog");
    const openDeleteButton = document.querySelector("[data-open-delete-dialog]");
    const closeDeleteButton = document.querySelector("[data-close-delete-dialog]");

    if (deleteDialog && openDeleteButton && closeDeleteButton) {
        openDeleteButton.addEventListener("click", () => {
            if (typeof deleteDialog.showModal === "function") {
                deleteDialog.showModal();
            }
        });
        closeDeleteButton.addEventListener("click", () => deleteDialog.close());
        deleteDialog.addEventListener("click", (event) => {
            if (event.target === deleteDialog) deleteDialog.close();
        });
    }
});
