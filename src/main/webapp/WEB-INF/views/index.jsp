<%@ page import="java.util.Map" %>
<%-- Iterate over the shortened URLs stored in the service --%>
<% Map<String, String> shortenedUrls = (Map<String, String>) request.getAttribute("shortenedUrls");
if (shortenedUrls != null) {
    for (Map.Entry<String, String> entry : shortenedUrls.entrySet()) { %>
        <li>
            Short URL: <a href="<%= entry.getKey() %>"><%= entry.getKey() %></a>
            Original URL: <%= entry.getValue() %>
        </li>
<%  }
} %>

<!DOCTYPE html>
<html>
<head>
    <title>URL Shortener</title>
</head>
<body>
    <h1>URL Shortener</h1>

    <form action="/api/shorten" method="post">
        <label for="url">Original URL:</label>
        <input type="text" id="url" name="url" required>
        <button type="submit">Shorten</button>
    </form>
    
    <hr>

    <h2>Shortened URLs</h2>
    <ul>
        <%-- Iterate over the shortened URLs stored in the service --%>
        <% if (shortenedUrls != null) {
            for (Map.Entry<String, String> entry : shortenedUrls.entrySet()) { %>
                <li>
                    Short URL: <a href="<%= entry.getKey() %>"><%= entry.getKey() %></a>
                    Original URL: <%= entry.getValue() %>
                </li>
        <%  }
        } %>
    </ul>
</body>
</html>
