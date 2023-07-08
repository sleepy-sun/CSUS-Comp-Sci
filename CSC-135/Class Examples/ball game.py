import pygame

# Define some colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

# Initialize Pygame
pygame.init()

# Set the width and height of the screen [width, height]
size = (700, 500)
screen = pygame.display.set_mode(size)

# Set the title of the window
pygame.display.set_caption("Bouncing Ball")

# Loop until the user clicks the close button
done = False

# Used to manage how fast the screen updates
clock = pygame.time.Clock()

# Starting position of the ball
x_pos = 50
y_pos = 50

# Speed and direction of the ball
x_speed = 5
y_speed = 5

# Main game loop
while not done:
    # Process events (keystrokes, mouse clicks, etc)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            done = True

    # Clear the screen
    screen.fill(BLACK)

    # Draw the ball
    pygame.draw.circle(screen, WHITE, [x_pos, y_pos], 20)

    # Update the position of the ball
    x_pos += x_speed
    y_pos += y_speed

    # Check for collision with the walls
    if x_pos >= size[0] - 20 or x_pos <= 20:
        x_speed = -x_speed
    if y_pos >= size[1] - 20 or y_pos <= 20:
        y_speed = -y_speed

    # Update the screen
    pygame.display.flip()

    # Limit frames per second
    clock.tick(60)

# Close the window and quit
pygame.quit()
