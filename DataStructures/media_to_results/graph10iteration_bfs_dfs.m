% Your data
iterations = 1:10;  % Replace with your actual iteration numbers
dfsTimes = [432600, 62900, 67100, 33000, 26300, 28200, 26200, 50800, 31900, 23800];  % Replace with your actual DFS times
bfsTimes = [85500, 87600, 51300, 24100, 29900, 38300, 33800, 24400, 20600, 20300];  % Replace with your actual BFS times

% Plot DFS and BFS times in the same plot
figure;
plot(iterations, dfsTimes, '-o', 'LineWidth', 2, 'DisplayName', 'DFS');
hold on;
plot(iterations, bfsTimes, '-s', 'LineWidth', 2, 'DisplayName', 'BFS');
hold off;

title('DFS and BFS Time Comparison');
xlabel('Iteration');
ylabel('Time (nanoseconds)');
legend('Location', 'Best');
grid on;
