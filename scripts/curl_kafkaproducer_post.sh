export TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ4d3hQZk5tdk9LajJpQmpHUVRNaTlYR1dVOFJ5VzA5eEdreGlBeGFvZjZvIn0.eyJleHAiOjE2NjQ5Njk4NjcsImlhdCI6MTY2NDg4MzQ2NywiYXV0aF90aW1lIjoxNjY0ODgzNDY3LCJqdGkiOiJjMWEzNWY4OC1iMTAzLTRkMWQtYTUxNi0wMDg4MTI4NWZhOTIiLCJpc3MiOiJodHRwOi8vc3NvLWFkY3VidW0tc3lyaXVzLnRlYW0tdWktZnJvbnRlbmRzZXJ2aWNlcy5hcHBzLmRldnYxLmNsdXN0ZXJzLmFkY3VidW0uY29tL2F1dGgvcmVhbG1zL2FkY3VidW0tc3lyaXVzIiwiYXVkIjpbImZyb250ZW5kc2VydmljZXMtYmZmIiwic3lyaXVzLWVycC1hcHAtc2VydmVyIiwibWQtZ3JwYy10ZXN0aW5nIiwic3lyaXVzLWVycC1wcmVzZW50YXRpb24tc2VydmVyLW9hdXRoMi1jbGllbnQiLCJhcGlnYXRld2F5Il0sInN1YiI6IjBlM2Q4NWYyLTdlMTctNDg0Ny1hYTlmLWI0M2RlNGMxZjBlYyIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaWdhdGV3YXkiLCJzZXNzaW9uX3N0YXRlIjoiMTI2N2I5ZmEtOWMzYS00YzkzLWFiMWEtOGFhMjYwMTMzODk1IiwiYWNyIjoiMSIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJSb2dlciBNw7xsbGVyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoicm9nZXIubXVlbGxlciIsImdpdmVuX25hbWUiOiJSb2dlciIsImZhbWlseV9uYW1lIjoiTcO8bGxlciIsImVtYWlsIjoicm9nZXIubXVlbGxlckBhZGN1YnVtLmNvbSJ9.a2z9yzMYgJ3cjlH4f3Hl5lIVL-OFK1LI7mesfgpN127aT_LM-x8rH6MAT-rLL2AiB3BuR2v1JfJXwERIpgOAhrohVZ6LxgUJ9Up2bJOtdgc6jdCvEvvXsHTyKR4D7NFl8KibQssdSdJAKoNf-qRkZFeKuSV9Utmlaok_3RoFpocYj29dsI8APlz6FHhKb-Wa5vSK9bL7DXxIEaYsKLEGQbugE_AJGzlOCG0Dbds8f6ZRvg6-yggWzMpglNiMrPKGX0hngbetq0tBm1lxigKvOAgvSQ32pWOAIQVMSjIyuZRReTfyi5-L65OgsOG79F1o9_5uaXQApCGtFULXmzzi8Q

curl -X POST -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" -d '{"new message from service"}' http://localhost:7070/api/v1/messages