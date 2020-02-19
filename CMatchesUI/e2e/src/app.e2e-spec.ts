import { AppPage } from './app.po';
import { browser, by,logging, element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to CMatchesUI!');
  });

//   it('should display title of application', ()=>{
//     page.navigateTo();
//     expect(browser.getTitle()).toEqual('CMatchesUI');
//   });

//   it('should be redirected to /register route', ()=>{
//     browser.element(by.css('.register-button')).click();
//     expect(browser.getCurrentUrl()).toContain('/register');
//     browser.driver.sleep(1000);
//   });

//   it('should be able to register user', ()=>{
//     browser.element(by.id('username')).sendKeys("shuv");
//     //browser.element(by.id('email')).sendKeys("shuv@gmail.com");
//     browser.element(by.id('password')).sendKeys("12345");
//     browser.element(by.css('.register-user')).click();
//     browser.driver.sleep(1000);
//   });

//   it('should be able to login user', ()=>{
//     browser.element(by.id('username')).sendKeys("shuv");
//     browser.element(by.id('password')).sendKeys("12345");
//     browser.element(by.css('.login-click')).click();
//     browser.driver.sleep(1000);
//   });

//   it('should be able to click on Menu item for Upcomongmatches', ()=>{
//     browser.element(by.css('.mat-button')).click();
//     browser.driver.sleep(1000);
//     browser.element(by.css('.mat-menu-item-newmatch')).click();
//     expect(browser.getCurrentUrl()).toContain('/UpcomingMatches');
//     browser.driver.sleep(1000);
//   });

//  it('should be able to save Upcoming match to Favourites', ()=>{
//   browser.driver.manage().window().maximize();
//   browser.driver.sleep(1000);
//   const tracks = element.all(by.css('.example-card'));
//   browser.driver.sleep(1000);
//   browser.element(by.css('.gridaddbutton')).click();
//   browser.driver.sleep(1000);
//  });

//  it('should be able to click on Menu item for OldMatches', ()=>{
//   browser.element(by.css('.mat-button')).click();
//   browser.driver.sleep(1000);
//   browser.element(by.css('.mat-menu-item-oldmatch')).click();
//   expect(browser.getCurrentUrl()).toContain('/OldMatches');
//   browser.driver.sleep(1000);
// });

// it('should be able to save Oldmatch to Favouritest', ()=>{
//  browser.driver.manage().window().maximize();
//  browser.driver.sleep(1000);
//  const tracks = element.all(by.css('.example-card'));
//  browser.driver.sleep(1000);
//  browser.element(by.css('.addbutton')).click();
//  browser.driver.sleep(1000);
// });

// it('should be able to get all data from Favourites', ()=>{
//   browser.driver.sleep(1000);
//   browser.element(by.css('.mat-button-favlist')).click();
//   expect(browser.getCurrentUrl()).toContain('/FavouriteList');
//   browser.driver.sleep(1000);
// });

// it('should be able to delete data from favourites', ()=>{
//   browser.driver.sleep(1000);
//   const tracks = element.all(by.css('.example-card'));
//   browser.driver.sleep(1000);
//   browser.element(by.css('.deletebutton')).click();
//   browser.driver.sleep(1000);
//  });

//  it('should be able to get all data from recommendations', ()=>{
//   browser.driver.sleep(1000);
//   browser.element(by.css('.mat-button-reslist')).click();
//   expect(browser.getCurrentUrl()).toContain('/recommendations');
//   browser.driver.sleep(1000);
// });

//  it('should be able to logout from application', ()=>{
//   browser.driver.sleep(1000);
//   browser.element(by.css('.mat-button-logout')).click();
//   browser.driver.sleep(1000);
//  });
});
