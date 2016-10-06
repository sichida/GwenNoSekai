import { GwenNoSekaiPage } from './app.po';

describe('gwen-no-sekai App', function() {
  let page: GwenNoSekaiPage;

  beforeEach(() => {
    page = new GwenNoSekaiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
