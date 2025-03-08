document.addEventListener('DOMContentLoaded', () => {
    const urlInput = document.getElementById('urlInput');
    const shortenBtn = document.getElementById('shortenBtn');
    const resultDiv = document.getElementById('result');
    const shortUrlInput = document.getElementById('shortUrl');
    const copyBtn = document.getElementById('copyBtn');
    const errorDiv = document.getElementById('error');
    const loadingDiv = document.getElementById('loading');

    shortenBtn.addEventListener('click', async () => {
        const longUrl = urlInput.value.trim();

        // Basic URL validation
        if (!longUrl) {
            showError('Please enter a URL');
            return;
        }

        try {
            new URL(longUrl);
        } catch (err) {
            showError('Please enter a valid URL');
            return;
        }

        // Show loading state
        loadingDiv.classList.remove('hidden');
        errorDiv.classList.add('hidden');
        resultDiv.classList.add('hidden');

        try {
            // Replace this URL with your actual backend API endpoint
            const response = await fetch('/url', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ url: longUrl })
            });

            if (!response.ok) {
                throw new Error('Failed to shorten URL');
            }

            const data = await response.json();
            shortUrlInput.value = data.shortUrl; // Adjust according to your API response
            resultDiv.classList.remove('hidden');
        } catch (error) {
            showError('Failed to shorten URL. Please try again.');
        } finally {
            loadingDiv.classList.add('hidden');
        }
    });

    copyBtn.addEventListener('click', () => {
        shortUrlInput.select();
        document.execCommand('copy');

        // Visual feedback for copy
        const originalText = copyBtn.textContent;
        copyBtn.textContent = 'Copied!';
        setTimeout(() => {
            copyBtn.textContent = originalText;
        }, 2000);
    });

    function showError(message) {
        errorDiv.textContent = message;
        errorDiv.classList.remove('hidden');
    }
});
