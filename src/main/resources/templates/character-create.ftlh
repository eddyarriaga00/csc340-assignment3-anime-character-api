<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Character</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<header>
    <h1>Create Character</h1>
    <nav>
        <a href="/characters">All Characters</a>
        <a href="/about">About</a>
    </nav>
</header>

<main>
    <form action="/characters/create" method="post" class="form-card">
        <label for="name">Name</label>
        <input id="name" type="text" name="name" required>

        <label for="series">Series</label>
        <input id="series" type="text" name="series" required>

        <label for="category">Category</label>
        <input id="category" type="text" name="category" required>

        <label for="signatureAbility">Signature Ability</label>
        <input id="signatureAbility" type="text" name="signatureAbility" required>

        <label for="description">Description</label>
        <textarea id="description" name="description" rows="5" required></textarea>

        <button type="submit">Create Character</button>
    </form>
</main>
</body>
</html>
