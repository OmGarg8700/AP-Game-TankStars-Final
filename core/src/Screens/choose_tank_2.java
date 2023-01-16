package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class choose_tank_2 extends ScreenAdapter implements Screen {
    Texture background;
    Viewport viewport;
    MyGdxGame game;
    OrthographicCamera cam;
    SpriteBatch batch;
    Stage stage;

    Label COINS;
    Label Gems;
    float elapsed;
    public choose_tank_2(MyGdxGame g){
        System.out.println("choose tank is called");
        this.game = g;
        cam = new OrthographicCamera();
        viewport = new FitViewport(game.getSCREEN_WIDTH(), game.getSCREEN_HEIGHT(), cam);
        batch = game.getBatch();
        cam.setToOrtho(false, game.getSCREEN_WIDTH(), game.getSCREEN_HEIGHT());
        stage = new Stage(viewport);
    }

    @Override
    public void render(float delta) {
        elapsed += delta;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        batch.draw(background, 0, 0, game.getSCREEN_WIDTH(), game.getSCREEN_HEIGHT());
        batch.draw(game.database.tank3.animation.getKeyFrame(elapsed), 10f, 190f, 1000, 318);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("choose_tank_2.jpg"));
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));

        final TextButton PLAY = new TextButton("PLAY",skin);
        final TextButton UPGARDE = new TextButton("UPGRADE", skin);


        Texture sett = new Texture(Gdx.files.internal("back-button.png"));
        TextureRegion sett_r = new TextureRegion(sett);
        TextureRegionDrawable sett_dr = new TextureRegionDrawable(sett_r);
//

        Texture add_button = new Texture(Gdx.files.internal("add_button.png"));
        TextureRegion add_button_c_r = new TextureRegion(add_button);
        TextureRegionDrawable add_button_c_dr = new TextureRegionDrawable(add_button_c_r);

        Texture forw = new Texture("forward-tanks.jpg");
        TextureRegion forward_r = new TextureRegion(forw);
        TextureRegionDrawable forward_dr = new TextureRegionDrawable(forward_r);
        Texture back = new Texture("back_tanks.jpg");
        TextureRegion backward_r = new TextureRegion(back);
        TextureRegionDrawable backward_dr = new TextureRegionDrawable(backward_r);

        ImageButton Settings = new ImageButton(sett_dr);
        ImageButton plus_coins = new ImageButton(add_button_c_dr);
        ImageButton forward = new ImageButton(forward_dr);
        ImageButton backward = new ImageButton(backward_dr);
        ImageButton plus_gems = new ImageButton(add_button_c_dr);

        Settings.setSize(101, 104);
        Settings.setPosition(50, 937);
        plus_coins.setSize(69, 69);
        plus_coins.setPosition(1590, 978);
        plus_gems.setSize(69, 69);
        plus_gems.setPosition(1828, 980);

        forward.setSize(60, 60);
        backward.setSize(38,41);
        backward.setPosition(1120, 535);
        forward.setPosition(1830, 535);

        PLAY.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new play(game, game.database.tank3, game.database.tank3_flip));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                PLAY.getLabel().setFontScale(2, 2);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                PLAY.getLabel().setFontScale(1,1);
            }
        });

        UPGARDE.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Upgrade_screen(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                UPGARDE.getLabel().setFontScale(2, 2);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                UPGARDE.getLabel().setFontScale(1,1);
            }
        });

        plus_coins.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new conversion(game));
            }
        });

        plus_gems.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new conversion(game));
            }
        });

        Settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.main_screen.getInstance());
            }
        });

        forward.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen((Screen) game.factory.screen("choose_tank_3", game));
            }
        });

        backward.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen((Screen) game.factory.screen("choose_tank_1", game));
            }
        });

        PLAY.setSize(300, 100);
        PLAY.setPosition(1350, 250);
        UPGARDE.setSize(300, 100);
        UPGARDE.setPosition(1350, 70);


        COINS = new Label(Integer.toString(game.database.COINS) ,skin, "white");
        Gems = new Label(Integer.toString(game.database.GEMS), skin, "white");
        COINS.setScale(100, 100);

        Gems.setPosition(1550, 1000);
        COINS.setPosition(1790, 1000);

        stage.addActor(PLAY);
        stage.addActor(UPGARDE);
        stage.addActor(Settings);
        stage.addActor(plus_coins);
        stage.addActor(plus_gems);
        stage.addActor(backward);
        stage.addActor(forward);
        stage.addActor(COINS);
        stage.addActor(Gems);

    }

    @Override
    public void dispose() {

    }
}
